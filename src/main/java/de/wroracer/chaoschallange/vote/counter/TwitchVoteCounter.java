package de.wroracer.chaoschallange.vote.counter;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.core.EventManager;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;
import com.github.twitch4j.chat.TwitchChat;
import com.github.twitch4j.chat.TwitchChatBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.util.EventManagerUtils;
import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

//import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import de.wroracer.chaoschallange.config.MainConfig;

public class TwitchVoteCounter {

    private  TwitchClient client;
    private String channelName = "wroracer";

    private ChaosManager manager;

    public List<String> hasVoted;
    private List<String> channels;

    public TwitchVoteCounter( ChaosManager manager,MainConfig config) {
        this.manager = manager;
        channels = new ArrayList<>();
        channels.add(config.getChannel());
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
                    if (!hasVoted.contains(user)){
                        try {
                            int nbr = Integer.parseInt(message);
                            if (nbr >=1 && nbr!=5 && nbr <=9){
                                manager.vote(nbr);
                                hasVoted.add(user);
                            }
                        }catch (Exception ignored){Bukkit.broadcastMessage("§7[§5Twitch-Chat§7] <§f"+user+"§7> §d"+message);}
                    }else {
                        Bukkit.broadcastMessage("§7[§5Twitch-Chat§7] <§f"+user+"§7> §d"+message);
                    }
                }
            }
        });
        client.getChat().sendMessage(channelName,"Chaos Online");
        client.getChat().joinChannel(channelName);
    }
    public void disconect(){
        client.getChat().disconnect();
        client.getChat().leaveChannel(channelName);
        client.getChat().close();
        client.close();
    }


}
