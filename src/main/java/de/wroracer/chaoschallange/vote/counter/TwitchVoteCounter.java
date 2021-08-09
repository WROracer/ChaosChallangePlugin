package de.wroracer.chaoschallange.vote.counter;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.config.MainConfig;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

//import javax.xml.bind.Marshaller;

public class TwitchVoteCounter {

    private  TwitchClient client;
    private String channelName = "wroracer";

    private ChaosManager manager;

    public List<String> hasVoted;
    private List<String> channels;

    public TwitchVoteCounter( ChaosManager manager,MainConfig config) {
        this.manager = manager;
        channels = new ArrayList<>();
        String channelTemp = config.getChannel();
        System.out.println(channelTemp);
        for (String temp:channelTemp.split("::")) {
            System.out.println(temp);
            channels.add(temp);
        }
        channelName = config.getChannel();

        hasVoted = new ArrayList<>();
        OAuth2Credential credential = new OAuth2Credential("twitch", config.getOAuth2());
        client = TwitchClientBuilder.builder()
            .withEnableChat(true).withChatAccount(credential).build();
        client.getEventManager().onEvent(ChannelMessageEvent.class, new Consumer<ChannelMessageEvent>() {
            @Override
            public void accept(ChannelMessageEvent event) {
                String channel = event.getChannel().getName();
                String user = event.getUser().getName();
                String message = event.getMessage();
                if (channels.contains(channel)){
                    try {
                        int nbr = Integer.parseInt(message);
                          if (nbr >=1 && nbr!=5 && nbr <=9){
                                if (!hasVoted.contains(user)) {
                                    manager.vote(nbr);
                                    hasVoted.add(user);
                                }
                          }else {
                              Bukkit.broadcastMessage("§7[§5Twitch-"+channel+"§7] <§f"+user+"§7> §d"+message);
                          }
                        }catch (Exception ignored){Bukkit.broadcastMessage("§7[§5Twitch-"+channel+"§7] <§f"+user+"§7> §d"+message);}
                }
            }
        });

        channels.forEach(channel->{
            System.out.println("Connecting To Twitch Channel: "+channel);
            client.getChat().sendMessage(channel,"Chaos Online");
            client.getChat().joinChannel(channel);
        });
    }
    public void disconect(){
        try {
            channels.forEach(channel->{
                client.getChat().leaveChannel(channel);
            });
            client.getChat().leaveChannel(channelName);
            client.getChat().disconnect();
        }catch (Exception ignored){}

    }


}
