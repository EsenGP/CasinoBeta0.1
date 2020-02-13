import javax.swing.*;
import java.util.Random;

public class Casino {
    public static void main(String[] args){
    String[] word = {"Первый","Второй"};
    int multiply;
    int[] balance = InputBalance(0,1);
    int i;
    while (balance[0]>0&balance[1]>0) {
        int[] bet = BetCount(0, 1);
        if (bet[0]>balance[0]|bet[1]>balance[1]) {JOptionPane.showMessageDialog(null, "Игроки, ставка не может быть больше баланса!");
            continue;
        }
        int[] choice = BetChoice(0, 0);
        int rand = Randomizer();

        for (i = 0; i < 2; i++) {
            if (choice[i] == rand) {
                if (rand == 1) multiply = 49;
                else multiply = 1;
                balance[i] +=(multiply * bet[i]);
                JOptionPane.showMessageDialog(null, word[i] + " игрок, твоя ставка выиграла" +
                        "\nК твоему счёту прибавилось " + (multiply * bet[i]) + "\nТвой баланс состовляет " + balance[i] + " рублей");
            }
            else {
                balance[i] -= bet[i];
                JOptionPane.showMessageDialog(null, word[i] + " игрок, твоя ставка проиграла" +
                        "\nС твоего счёта убавилось " + bet[i] + "\nТвой баланс состовляет " + balance[i] + " рублей");
            }
        }
        if (balance[0]+balance[1]<1) JOptionPane.showMessageDialog(null,"Оба игрока потратили весь банк");
        else if (Math.min(balance[0],balance[1])<1) {
            if (balance[0]<balance[1]) i = 0 ; else i = 1;
            JOptionPane.showMessageDialog(null,word[i] +" игрок, у тебя закончились деньги!");
        }
    }

    }

    private static int[] InputBalance(int balance1, int balance2) {
        String answer = JOptionPane.showInputDialog(null, "Вы уже играли у нас?");
        if (answer.equals("Да") | answer.equals("Yes") | answer.equals("да") | answer.equals("yes") | answer.equals("ДА") | answer.equals("YES")) {
        balance1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Первый игрок, сколько у вас на балансе?"));
        balance2 = Integer.parseInt(JOptionPane.showInputDialog(null,"Второй игрок, сколько у вас на балансе?"));
        } else {
            JOptionPane.showMessageDialog(null, "Приветствуем вас в нашем казино." +
                    "\nВижу вас двое." +
                    "\nПрежде чем начнёте ставить реальные деньги, я предоставлю вам возможность поиграть на вымышленные деньги" +
                    "\nУ каждого из вас на балансе по 5000 руб.");
            balance1 = 5000; balance2 = 5000;
        }
        return new int[] {balance1,balance2};
    }

    private static int[] BetCount(int bet1 , int bet2) {
        while (bet1!=bet2) {
            while (bet1==0) {
                bet1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Первый игрок\nВведи сумму ставки"));
                if (bet1<0) bet1 *= (-1);
                else if (bet1==0) JOptionPane.showMessageDialog(null, "Ставка не может равняться нулю!");
            }
            bet2 = 0;
            while (bet2==0) {
                bet2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Второй игрок\nВведи сумму ставки"));
                if (bet2 < 0) bet2 *= (-1);
                else if (bet2 == 0) JOptionPane.showMessageDialog(null, "Ставка не может равняться нулю!");
            }
            if (bet1!=bet2) {JOptionPane.showMessageDialog(null,"Ставки рознятся!");
                bet1=0;}
        }
        return new int[] {bet1, bet2};
    }

    private static int[] BetChoice(int choice1, int choice2) {
        while (choice1<1|choice1>3) {
            choice1 = Integer.parseInt(JOptionPane.showInputDialog(null,"Первый игрок\nНа что поставишь?" +
                    "\n1)Зелёный(50х)\t2)Белый(2х)\t3)Чёрный(2х)"));
        }
        while (choice2<1|choice2>3) {
            choice2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Второй игрок\nНа что поставишь?" +
                    "\n1)Зелёный(50х)\t2)Белый(2х)\t3)Чёрный(2х)"));
        }
        return new int[] {choice1, choice2};
    }

    private static int Randomizer() {
        int rand;
        rand = new Random().nextInt(51);
        if (rand == 0) rand = 1;
        else if (rand%2 == 1) rand = 2;
        else if (rand%2 == 0) rand = 3;
        return rand;
    }

}