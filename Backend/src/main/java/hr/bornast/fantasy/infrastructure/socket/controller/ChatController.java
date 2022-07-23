//package hr.bornast.fantasy.infrastructure.socket.controller;
//
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.List;
//
//import hr.bornast.fantasy.infrastructure.socket.model.ChatEntity;
//import hr.bornast.fantasy.infrastructure.socket.model.MessageEntity;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin
//public class ChatController {
//
//    private SimpMessagingTemplate simpMessagingTemplate;
//    private final ChatDAO chatDAO;
//    private final MessageDAO messageDAO;
//
//    @MessageMapping("/chat/{to}") //to = nome canale
//    public void sendMessage(@DestinationVariable String to , MessageEntity message) {
//        System.out.println("handling send message: " + message + " to: " + to);
//        message.setChat_id(createAndOrGetChat(to));
//        message.setT_stamp(generateTimeStamp());
//        message = messageDAO.save(message);
//        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
//    }
//
//    @PostMapping("/getChats")
//    public List<ChatEntity> getChats(@RequestBody String user){
//        return chatDAO.findByPartecipant(user);
//    }
//
//    //returns an empty list if the chat doesn't exist
//    @PostMapping("/getMessages")
//    public List<MessageEntity> getMessages(@RequestBody String chat) {
//        ChatEntity ce = chatDAO.findByName(chat);
//
//        if(ce != null) {
//            return messageDAO.findAllByChat(ce.getChat_id());
//        }
//        else{
//            return new ArrayList<MessageEntity>();
//        }
//    }
//
//    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way
//    private Long createAndOrGetChat(String name) {
//        ChatEntity ce = chatDAO.findByName(name);
//
//        if (ce != null) {
//            return ce.getChat_id();
//        }
//        else {
//            ChatEntity newChat = new ChatEntity(name);
//            return chatDAO.save(newChat).getChat_id();
//        }
//    }
//
//    private String generateTimeStamp() {
//        Instant i = Instant.now();
//        String date = i.toString();
//        System.out.println("Source: " + i.toString());
//        int endRange = date.indexOf('T');
//        date = date.substring(0, endRange);
//        date = date.replace('-', '/');
//        System.out.println("Date extracted: " + date);
//        String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
//        time += ":";
//
//        int minutes = i.atZone(ZoneOffset.UTC).getMinute();
//        if (minutes > 9) {
//            time += Integer.toString(minutes);
//        } else {
//            time += "0" + Integer.toString(minutes);
//        }
//
//        System.out.println("Time extracted: " + time);
//        String timeStamp = date + "-" + time;
//        return timeStamp;
//    }
//
//}
