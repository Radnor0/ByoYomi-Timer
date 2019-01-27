import javax.swing.JOptionPane;

public class Main {

    private static int black, white, blackOvertimes, whiteOvertimes, overtimeDuration;
    private static boolean blackTurn = false;
    private static boolean ignoreTick = false;
    private static boolean blackOvertime = false;
    private static boolean whiteOvertime = false;
    private static Thread timer;
    
    private static Window win;

    public static void main(String[] args) {
        new Setup();

        timer = new Thread() {
            public void run() {
                while (blackOvertimes >= 0 && whiteOvertimes >= 0) {
                    try {
                        sleep(1000);
                        tick();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private static void tick() {
        //System.out.println("Tick");

        if (ignoreTick || !win.started) {
            ignoreTick = false;
            return;
        }

        if (blackTurn) {
            if (black > 1)
                black--;
            else {
                blackOvertime = true;
                black = overtimeDuration;
                blackOvertimes--;
            }
        }
        else {
            if (white > 1)
                white--;
            else {  
                whiteOvertime = true;
                white = overtimeDuration;
                whiteOvertimes--;
            }
        }

        if (blackOvertimes < 0 || whiteOvertimes < 0)
            endGame();

        updateTimes();
    }

    private static void endGame() {
        win.endGame(blackOvertimes >= 0);
        timer.stop();
    }

    public static void updateTimes() {
        win.update(white, black, whiteOvertimes, blackOvertimes);
        //System.out.print("Time Left: " + ((blackTurn) ? black : white));
    }

    public static void setup(String mainTime, int overTimeAmount, int overTimeDuration) {
        if (mainTime.charAt(0) == ':') {
            black = Integer.parseInt(mainTime.substring(1));
            white = black;
        }
        else {
            try {
                String[] timeSplit = mainTime.split(":");
                black = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
                white = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                black = Integer.parseInt(mainTime) * 60;
                white = Integer.parseInt(mainTime) * 60;
                mainTime = mainTime + ":00";
            }
        }

        blackOvertimes = overTimeAmount;
        whiteOvertimes = overTimeAmount;

        overtimeDuration = overTimeDuration;

        win = new Window(mainTime, overTimeAmount + "");
        timer.start();
    }

    public static void switchTurn() {
        blackTurn = !blackTurn;
        ignoreTick = true;
        if (blackTurn && blackOvertime)
            black = overtimeDuration;
        if (!blackTurn && whiteOvertime)
            white = overtimeDuration;
    }
}