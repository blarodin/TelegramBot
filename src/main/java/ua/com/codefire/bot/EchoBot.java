package ua.com.codefire.bot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EchoBot extends TelegramLongPollingBot {

    private final Log log = LogFactory.getLog(EchoBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(new StringBuilder(update.getMessage().getText()).reverse().toString());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return System.getenv().get("USERNAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv().get("APIKEY");
    }
}
