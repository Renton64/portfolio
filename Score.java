package mash;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Score {
    private static ArrayList<Score> ranking = new ArrayList<>();
    private String name;
    private int score;
    
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    //スコアの保存
    private static void saveScore(String name, int score) {
        
        ranking.add(name, score);

        // スコアをソート
        int n = ranking.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ranking.get(j).getScore() < ranking.get(j + 1).getScore()) {
                    // スコアを交換
                    Score temp = ranking.get(j);
                    ranking.set(j, ranking.get(j + 1));
                    ranking.set(j + 1, temp);
                }
            }
        }

        // ランキングを保存
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt"))) {
            for (Score s : ranking) {
                writer.write(s.getName() + "," + s.getScore() + ",");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
