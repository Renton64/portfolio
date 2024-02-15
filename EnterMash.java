package mash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EnterMash {

    private static ArrayList<Score> ranking = new ArrayList<>();

    public static void main(String[] args) {
    	
        Ranking rank = new Ranking();
        rank.loadRanking();

        //ゲームの事前説明
        System.out.println("連打ゲームを開始します。(制限時間10秒)");
        System.out.println("制限時間内にEnterキーを連打してください(押し続ける行為は禁止)");
        System.out.println("ゲームスタートの表示が出てからゲームスタートです\n");
        System.out.println("準備ができたらEnterキーを押してください(カウントダウンが始まります)");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ゲーム開始前のカウントダウン
        countDown();

        char targetKey      = '\n'; // Enterキー
        int gameTime        = 5; // ゲームの制限時間
        long gameStartTime  = System.currentTimeMillis();
        int totalHits       = 0;


        System.out.println("ゲームスタート!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (System.currentTimeMillis() - gameStartTime < gameTime * 1000) {
                char inputKey = (char) reader.read();
                if (inputKey == targetKey) {
                    totalHits++;
                    System.out.print(totalHits);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("ゲーム終了！");
        System.out.println("総連打数: " + totalHits + "\n");
        if(totalHits < 320) {       //連打数の制限

            // 名前を入力するためにプレイヤーに促す
            System.out.println("名前を入力してください (名前入力、enter押下後に\"end\" と入力して終了):");
            try {
                StringBuilder nameBuilder = new StringBuilder();
                String input;
                while (!(input = reader.readLine()).equalsIgnoreCase("end")) {
                    nameBuilder.append(input);
                }
                String name = nameBuilder.toString();
                saveScore(name, totalHits);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("押し続ける行為を発見、記録は残りません\n");
        }

        //ランキングの表示
        ranking.dispRanking();
    }

    //ゲーム開始前のカウントダウン
    public static void countDown() {
        int countdownDuration = 3; // カウントダウンの秒数

        for (int i = countdownDuration; i >= 0; i--) {
            System.out.println(i + "秒");

            try {
                Thread.sleep(1000); // 1秒待機
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
