package model;
public class Space {
	private int file;
	private int rank;
	
	/**
	 * @param r
	 * @param f
	 */
	public Space(int r, int f) {
		rank = r;
		file = f;
	}
	
	/**
	 * @param other
	 * @return
	 */
	public boolean equals(Space other) {
		return (rank == other.rank) && (file == other.file);
	}
	
	/**
	 * 
	 * @param rank
	 * @param file
	 * @return true if the given rank and file values match the space.
	 */
	public boolean equals(int rank, int file){
		return this.rank == rank && this.file == file;
	}
	
	/**
	 * @return file
	 */
	public int getFile() {
		return file;
	}
	
	/**
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(int file) {
		this.file = file;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

}