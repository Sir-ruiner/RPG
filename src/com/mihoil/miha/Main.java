package com.mihoil.miha;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // TODO: 13.02.2022 в main пропускать пролог если есть сохранение           |GREEN|
    // TODO: 13.02.2022 переписать save и load чтобы сохранялись хп игрока      |GREEN|
    // TODO: 13.02.2022 убрать щиткод в changeLocation и showAvialibleLocations 
    public static void save(Integer plrlvl, Integer plrxp, Integer plrhp, Integer hl, String plrname) {
        try {
            File savegame = new File("RPG.save");
            savegame.createNewFile();
            FileOutputStream fops = new FileOutputStream(savegame);
            fops.write((plrlvl.toString() + "\n").getBytes());
            fops.write((plrxp.toString() + "\n").getBytes());
            fops.write((hl.toString() + "\n").getBytes());
            fops.write((plrhp.toString() + "\n").getBytes());
            fops.write((plrname).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String load() {
        try {
            File savegame = new File("RPG.save");
            FileInputStream fips = new FileInputStream(savegame);
            String loadgame = new String(fips.readAllBytes());
            return loadgame;
        } catch (IOException e) {
            System.out.println("Файл сохранения не найден");
        }
        return null;
    }

    public static void showPlayerStats(Integer plrlvl, Integer plrhp, Integer plrxp, Integer hl) {
        System.out.println("LVL: " + plrlvl + " XP: " + plrxp + " HP: " + plrhp + " INVENTORY: Целебный элексир - " + hl);
        System.out.println("---------------------------------------------------------------------------------------------------");
        waitTime(1000);
    }

    public static Integer[] lvlup(Integer plrlvl, Integer plrxp, Integer plrhp, Integer hl) {
        if (plrxp >= 1000) {
            plrlvl = plrlvl + 1;
            plrxp = 0;
            System.out.println("УРОВЕНЬ ПОВЫШЕН!!!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            showPlayerStats(plrlvl, plrhp, plrxp, hl);
        }
        Integer[] lvlup = {plrlvl, plrxp};
        return lvlup;
    }

    public static Integer[] heal(Integer plrhp, Integer hl, Integer plrlvl, Integer plrxp, Scanner scnr) {
        System.out.println("Сейчас ты можешь использовать целебный элексир.");
        System.out.println("---------------------------------------------------------------------------------------------------");
        waitTime(1000);
        System.out.println("Чтобы это сделать нажми 1 или нажми enter если не хочешь это делать.");
        System.out.println("---------------------------------------------------------------------------------------------------");
        waitTime(1000);
        String chc = scnr.nextLine();
        if (chc.equals("1") & hl > 0) {
            if (plrhp >= 50) {
                System.out.println("Ты воспользовался целебным элексиром.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                hl = hl - 1;
                plrhp = 100;
                showPlayerStats(plrlvl, plrhp, plrxp, hl);
            } else {
                System.out.println("Ты воспользовался целебным элексиром.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                hl = hl - 1;
                plrhp = plrhp + 50;
                showPlayerStats(plrlvl, plrhp, plrxp, hl);
            }
        } else {
            System.out.println("Ты не воспользовался целебным элексиром.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            showPlayerStats(plrlvl, plrhp, plrxp, hl);
        }
        Integer[] hp = {plrhp, hl};
        return hp;
    }

    public static String[] changeLocation(Scanner scnr, Integer plrlvl, Integer plrxp, Integer plrhp, Integer hl, Integer ICC, String plrname) {
        String chngL = scnr.nextLine();
        Integer mhp = 0;
        Integer pxp = 0;
        if(chngL.equals("1")) {
            System.out.println("Ты прошёл в лес, встретил врагов.");
            mhp = 10;
            pxp = 100;
        } else if (chngL.equals("2")) {
            if(plrlvl>=2) {
                System.out.println("Ты отправился в Нордскол, встретил сильных врагов.");
                mhp = 20;
                pxp = 150;
            }
        } else if (chngL.equals("3")) {
            if(plrlvl>=3) {
                System.out.println("Ты отправился в дальний путь, в Восточные Королевства и встретил много сильных врагов.");
                mhp = 30;
                pxp = 200;
            }
        } else if (chngL.equals("4")) {
            if(plrlvl>=4) {
                System.out.println("С помощью порталла ты попал в Запределье и встретил множество врагов.");
                mhp = 40;
                pxp = 250;
            }
        } else if (chngL.equals("5")) {
            if(plrlvl>=5) {
                System.out.println("Через портал ты попал в Дренор и встретил в нём не мало очень сильных врагов.");
                mhp = 50;
                pxp = 400;
            }
        } else if (chngL.equals("6")) {
            if(plrlvl>=6 & ICC == 1) {
                System.out.println("Ты проник в Цитадель Ледяной Короны и встретил там могучего Лорда Ребрада!");
                mhp = 70;
                pxp = 500;
                ICC = ICC + 1;
            } else if(plrlvl>=6 & ICC == 2) {
                System.out.println("Отважный воин " + plrname + ", ты снова отправился в Цитадель Ледяной Короны!");
                mhp = 90;
                pxp = 750;
                ICC = ICC + 1;
            } else if(plrlvl>=6 & ICC == 3) {
                System.out.println("Это было очень смелое решение возвращаться сюда, " + plrname + "!");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("Ты заходишь в тронный зал и видишь его, величайшего Короля Лича!");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("<Артас встаёт с трона>");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("Артас: зачем ты пришёл? Действительно думаешь что способен меня одолеть?");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("Артас: чтож, надеюсь ты приготовился к смерти.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("Король Лич, Артас достаёт меч и в ту же секунду вонзает его в тебя.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                plrhp = plrhp - 100;
                System.out.println("Вот и конец истории, " + plrname + ".");
                System.out.println("Ты поистине великий воин, но каждой истории рано или поздно приходит конец.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.out.println("Орда тебя не забудет воин, прощай " + plrname + "!");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                System.exit(10);
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
        waitTime(1000);
        Random r = new Random();
        Integer chns = r.nextInt(100) + 1;
        if(chns <= 30) {
            hl += 1;
        }
        plrhp = plrhp - mhp;
        death(plrhp);
        System.out.println("И победил.");
        System.out.println("---------------------------------------------------------------------------------------------------");
        waitTime(1000);
        plrxp = plrxp + pxp;
        showPlayerStats(plrlvl, plrhp, plrxp, hl);
        Integer[] res = heal(plrhp, hl, plrlvl, plrxp, scnr);
        hl = res[1];
        plrhp = res[0];
        Integer[] lvl = lvlup(plrlvl, plrxp, plrhp, hl);
        plrxp = lvl[1];
        plrlvl = lvl[0];
        String[] changeLocation = {plrlvl.toString(), plrxp.toString(), plrhp.toString(), hl.toString(), ICC.toString()};
        return changeLocation;
    }

    public static void showAvailableLocations(Integer plrlvl) {
        String location = "";
        if (plrlvl >= 1) {
            location += "1: лес возле Оргриммара ";
        } else if (plrlvl >= 2) {
            location += "2: Нордскол ";
        } else if (plrlvl >= 3) {
            location += "3: Восточные королевства ";
        } else if (plrlvl >= 4) {
            location += "4: Запределье ";
        } else if (plrlvl >= 5) {
            location += "5: Дренор ";
        } else if (plrlvl >= 6) {
            location += "6: Цитадеть Ледяной Короны";
        }
        System.out.println(location);
    }

    public static void death(Integer plrhp) {
        if (plrhp <= 0) {
            waitTime(1000);
            System.out.println("Ты храбро сражался за честь Орды и умер, тебя не забудут! За Орду!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.exit(10);
        }
    }

    public static void waitTime(Integer ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Integer plrlvl = 1;
        Integer plrhp = 100;
        Integer plrxp = 0;
        Integer hl = 1;
        Integer ICC = 1;
        String loaded = load();
        String plrname;
        Scanner scnr = new Scanner(System.in);
        if(loaded != null) {
            String[] loadvars = loaded.split("\n");
            plrlvl = Integer.valueOf(loadvars[0]);
            plrxp = Integer.valueOf(loadvars[1]);
            plrhp = Integer.valueOf(loadvars[2]);
            hl = Integer.valueOf(loadvars[3]);
            plrname = loadvars[4];
            System.out.println("Сохранение успешно загружено.");
        } else {
            System.out.println("Добро пожаловать в RPG! Введите ваш никнейм");
            plrname = scnr.nextLine();
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Добро пожаловать в Оргриммар, " + plrname + "!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Ты молодой воин Орды и тебе предстоит многому научиться.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Твои показатели, такие как уровень, здоровье и опыт будут всегда отображены так.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            showPlayerStats(plrlvl, plrhp, plrxp, hl);
            System.out.println("Каждую 1000 XP твой уровень повышается.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Ты можешь посещать разные локации для прокачки, но будь осторожен, ведь там много опасных зверей и врагов!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Они могут убить тебя, если у тебя не получится их победить.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Ты можешь восстановить своё здоровье с помощью целебного элексира, если получил серьёзный урон во время битвы.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("С новым уровнем будут открываться новые локации.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("В новых локациях прокачка даётся быстрее, но там более опасные враги.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Пока что тебе доступен только лес, сходи туда и попрактикуйся.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            showAvailableLocations(plrlvl);
            try {
                Integer chc1 = Integer.valueOf(scnr.nextLine());
                if (chc1 == 1) {
                    plrhp = plrhp - 10;
                    plrxp = plrxp + 50;
                    showPlayerStats(plrlvl, plrhp, plrxp, hl);
                }
            } catch (NumberFormatException e) {
                System.out.println("Оу, тебе нужно было нажать номер локации, куда нужно пойти, но ничего, ты всеравно пошёл в лес.");
                System.out.println("---------------------------------------------------------------------------------------------------");
                waitTime(1000);
                plrhp = plrhp - 10;
                plrxp = plrxp + 50;
                showPlayerStats(plrlvl, plrhp, plrxp, hl);
            }
            waitTime(1000);
            System.out.println("Ты сходил в лес и получил небольшое ранение.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("У тебя в инвентаре наверное есть целебный элексир, чтобы залечиться. Один целебный элексир восстанавливает 50hp");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("У тебя есть один целебный элексир, теперь подличи себя. Чтобы использовать целебный элексир нажми 1.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            try {
                Integer invchc1 = Integer.valueOf(scnr.nextLine());
                if (invchc1 == 1) {
                    if (plrhp > 50) {
                        plrhp = 100;
                        hl = hl - 1;
                        showPlayerStats(plrlvl, plrhp, plrxp, hl);
                    }
                }
            } catch (NumberFormatException e) {
                showPlayerStats(plrlvl, plrhp, plrxp, hl);
            }
            waitTime(1000);
            System.out.println("В дальнейшем, если ты не хочешь использовать целебный элексир, то просто нажми enter.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Отлично, теперь ты вернулся в Оргриммар, тут безопасно и можно отдохнуть.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Ну воин, на этом я с тобой прощаюсь.");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("Я обучил тебя всему необходимому и теперь ты готов сражаться за честь Орды!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("На последок возьми этот меч воина и целебный элексир.");
            hl = hl + 1;
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            System.out.println("За Орду!");
            System.out.println("---------------------------------------------------------------------------------------------------");
            waitTime(1000);
            showPlayerStats(plrlvl, plrhp, plrxp, hl);
        }
        while(true) {
            showAvailableLocations(plrlvl);
            String[] cL = changeLocation(scnr, plrlvl, plrxp, plrhp, hl, ICC, plrname);
            plrlvl = Integer.valueOf(cL[0]); //a, b
            plrxp = Integer.valueOf(cL[1]);
            plrhp = Integer.valueOf(cL[2]);
            hl = Integer.valueOf(cL[3]);
            ICC = Integer.valueOf(cL[4]);
            save(plrlvl, plrxp, plrhp, hl, plrname);
        }
    }
}
