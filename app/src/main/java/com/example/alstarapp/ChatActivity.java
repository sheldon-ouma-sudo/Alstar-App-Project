package com.example.alstarapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = " ChatActivity";
    final ParseUser currentUser = ParseUser.getCurrentUser();
    TextView tvMessageTitle;
    EditText etEnterMessage;
    Button btnSend;
    RecyclerView rvChat;
    ArrayList<Message> mMessages;
    ChatAdapter chatAdapter;
    // Keep track of initial load to scroll to the bottom of the ListView
    boolean mFirstLoad;
    ParseUser receivingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // get elements
        etEnterMessage = findViewById(R.id.etEnterMessage);
        btnSend = findViewById(R.id.btnSend);
        rvChat = findViewById(R.id.rvChat);
        mMessages = new ArrayList<>();
        mFirstLoad = true;
        tvMessageTitle = findViewById(R.id.tvMessagingTitle);

        // get data from intent

        Review review = getIntent().getParcelableExtra("reviewer");
        receivingUser = review.getReviewer();
        if (receivingUser == null) {
            Log.i(TAG, "Receiving user is null");
        }
        Log.i(TAG, "The reviewer is" + receivingUser.getUsername());
        // set title
        tvMessageTitle.setText("Chatting with " + receivingUser.getUsername());

        // set up adapter
        chatAdapter = new ChatAdapter(mMessages, ChatActivity.this);
        rvChat.setAdapter(chatAdapter);

        // Create a handler which can run code periodically
        final int POLL_INTERVAL = 1000; // milliseconds
        final Handler myHandler = new Handler(Looper.getMainLooper());
        Runnable mRefreshMessagesRunnable = new Runnable() {
            @Override
            public void run() {
                refreshMessages(currentUser, receivingUser); // change to second user
                myHandler.postDelayed(this, POLL_INTERVAL);
            }
        };

        // refresh
        myHandler.postDelayed(mRefreshMessagesRunnable, POLL_INTERVAL);

        // associate the LayoutManager with the RecyclerView
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        linearLayoutManager.setReverseLayout(false);
        rvChat.setLayoutManager(linearLayoutManager);

        // when send button clicked, send message
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = etEnterMessage.getText().toString();
                if (body.isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Please enter a message to send", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessage(currentUser, receivingUser, body); // change to second user
            }
        });
    }

    private void sendMessage(final ParseUser userSending, final ParseUser userReceiving, String body) {
        final Message newMessage = new Message();
        newMessage.setUserSending(userSending);
        newMessage.setMessageBody(body);

        // save to database
        newMessage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(ChatActivity.this, "Issue sending message; please try again", Toast.LENGTH_SHORT).show();
                } else {
                    // if successful, clear field
                    etEnterMessage.setText("");
                    addToConvo(userSending, userReceiving, newMessage);
                }
            }
        });
    }

    private void addToConvo(final ParseUser userSending, final ParseUser userReceiving, final Message newMessage) {
        // query convos
        final ParseQuery<Conversation> convos = ParseQuery.getQuery(Conversation.class);
        convos.whereEqualTo(Conversation.KEY_personOne, userSending);
        convos.whereEqualTo(Conversation.KEY_personTwo, userReceiving);
        convos.getFirstInBackground(new GetCallback<Conversation>() {
            @Override
            public void done(Conversation object, ParseException e) {
                if (e != null) {
                    Log.d("ChatActivty", "request failed");
                    // if convo does not exist, make new convos and save
                    Conversation newConvo1 = makeConvo(userSending, userReceiving, newMessage);
                    Conversation newConvo2 = makeConvo(userReceiving, userSending, newMessage);
                } else {
                    Log.d("ChatActivity", object.getPersonOne().getObjectId());

                    // if a convo exists, add message to convo
                    if (object != null) {
                        addMessage(object, newMessage);

                        // then add message to convo going the other way
                        ParseQuery<Conversation> convos2 = ParseQuery.getQuery(Conversation.class);
                        convos2.whereEqualTo(Conversation.KEY_personTwo, userSending);
                        convos2.whereEqualTo(Conversation.KEY_personOne, userReceiving);
                        convos2.getFirstInBackground(new GetCallback<Conversation>() {
                            @Override
                            public void done(Conversation object2, ParseException e) {
                                if (e != null) {
                                    Log.d("ChatActivty", "request failed2");
                                } else {
                                    addMessage(object2, newMessage);
                                }
                            }
                        });
                    } else {
                        // if convo does not exist, make new convos and save
                        Conversation newConvo1 = makeConvo(userSending, userReceiving, newMessage);
                        Conversation newConvo2 = makeConvo(userReceiving, userSending, newMessage);
                    }
                }
            }
        });
    }

    private Conversation makeConvo(ParseUser userSending, ParseUser userReceiving, Message message) {
        final Conversation newConvo = new Conversation();
        newConvo.setPersonOne(userSending);
        newConvo.setPersonTwo(userReceiving);
        List<Message> newList = new ArrayList<>();
        newList.add(message);
        newConvo.setMessage(newList);
        newConvo.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("ChatActivty", "error creating convo");
                }
            }
        });
        return newConvo;
    }

    private void addMessage(Conversation object, Message newMessage) {
        Object messageListObj = object.getMessages();
        ArrayList<Message> messageList = (ArrayList<Message>) messageListObj;
        messageList.add(newMessage);
        object.setMessage(messageList);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("ChatActivty", "issue saving convo");
                }
            }
        });
    }

    // Query messages from Parse so we can load them into the chat adapter
    void refreshMessages(ParseUser sender, ParseUser receiver) {
        ParseQuery<Conversation> queryToShow = ParseQuery.getQuery(Conversation.class);
        queryToShow.whereEqualTo(Conversation.KEY_personOne, sender);
        queryToShow.whereEqualTo(Conversation.KEY_personTwo, receiver);
        queryToShow.getFirstInBackground(new GetCallback<Conversation>() {
            @Override
            public void done(Conversation object, ParseException e) {
                if (e != null) {
                    Log.e("message", "Error Loading convos" + e);
                } else {
                    try {
                        Object convoMessages = ((Conversation) object.fetchIfNeeded()).getMessages();
                        ArrayList<Message> convoMessagesList = (ArrayList<Message>) convoMessages;
//                        Collections.reverse(convoMessagesList);
                        mMessages.clear();
                        mMessages.addAll(convoMessagesList);
                        chatAdapter.notifyDataSetChanged();
                        // Scroll to the bottom of the list on initial load
                        if (mFirstLoad) {
                            rvChat.scrollToPosition(0);
                            mFirstLoad = false;
                        }

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
