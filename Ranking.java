package mash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import mash.Score;

public class Ranking {

    private static ArrayList<Score> ranking = new ArrayList<>();
    String rankFile = "ranking.txt";    //ランキング保存用テキストファイル

    //ランキングを指定ファイルからロード
    private void loadRanking() {
        try (BufferedReader br = new BufferedReader(new FileReader("rankFile"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    ranking.add(new Score(name, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ランキングを保存
    private static void saveRanking() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rankFile"))) {
            for (Score s : ranking) {
                writer.write(s.getName() + "," + s.getScore() + ",");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ランキングの表示
    private static void dispRanking() {
        System.out.println("ランキング:");
        for (int i = 0; i < Math.min(5, ranking.size()); i++) {
            Score score = ranking.get(i);
            System.out.println((i + 1) + ". " + score.getName() + ": " + score.getScore() + "回");
        }
    }
}
