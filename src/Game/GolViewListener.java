package Game;

	public interface GolViewListener {
	
		void handleGolViewEvent();
		void resize(int size);
		void appearModel();
		void changeState(boolean[][] temp);
		void clearBoard();
}
