public class Main {
    public static void main(String[] args) {
        System.out.println("Run...");
        BattleshipGame game=new BattleshipGame();
        game.drawGrids();
        game.getPlayerA().setupShips();
        game.drawGrids();
    }
}
