package com.catch_my_hand.backend;


import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

public class SlackmessageService {
    public static WebhookResponse send(String text) {
        try {
            WebhookResponse response = null;
            Slack slack = Slack.getInstance();

            String webhookUrl = "https://hooks.slack.com/services/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";

            Payload payload = Payload.builder().text(text).build();
            response = slack.send(webhookUrl, payload);
            return response;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
