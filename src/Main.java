public class Main {
    public static void main(String[] args) {
        System.out.println("Run...");
        BattleshipGame game = new BattleshipGame();
        game.getPlayerA().drawGrids();
        game.getPlayerA().setupShips();
        game.getPlayerA().drawGrids();
    }
}
