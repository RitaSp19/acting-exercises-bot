package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExerciseScheduler {
    private final ExerciseBot bot;
    private final ExerciseService exerciseService;

    public ExerciseScheduler(ExerciseBot bot, ExerciseService exerciseService) {
        this.bot = bot;
        this.exerciseService = exerciseService;
    }

    @Scheduled(cron = "${exercise.time.cron}")
    public void sendDailyExercise() {
        String exercise = exerciseService.getRandomExercise();
        bot.sendTextMessage(bot.getTargetChatId(), "Сегодняшнее упражнение:\n\n" + exercise);
    }
}