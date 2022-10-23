import student.tetris.*;
//-------------------------------------------------------------------------
/**
 *  This is a "brain" being designed to find a strategic way
 *  to play Tetris.
 *
 *  @author Nana Yaw Barimah Oteng 
 *  @version (2022.03.31)
 */
public class CleverBrain
    implements Brain   
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created CleverBrain object.
     */
    public CleverBrain()
    {
        super();
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * This helps a tetris piece choose the right move.
     * @param board represents the tetris board.
     * @param piece represents a piece on the board.
     * @param heightLimit reserves a certain portion at the top of a board
     * for new pieces.
     * @return returns the best move for the piece.
     */
    public Move bestMove(Board board, Piece piece, int heightLimit)
    {
        Move newMove = new Move(piece);
        Piece[] pieceRot = piece.getRotations();
        newMove.setScore(1000000.0);
        
        for (int j = 0; j < pieceRot.length; j++)
        {
            Piece rotPiece = pieceRot[j];
            int pieceWidth = rotPiece.getWidth();
            for (int i = 0; i < board.getWidth() - pieceWidth + 1; i++)
            {
                Point destination = new Point(
                    i, board.rowAfterDrop(rotPiece, i));
                board.place(rotPiece, destination);
                board.clearRows();
                double rating = rateBoard(board);
                
                if (rating < newMove.getScore() && 
                    (board.getColumnHeights()[i] < heightLimit))
                {
                    newMove.setScore(rating);
                    newMove.setLocation(destination);
                    newMove.setPiece(rotPiece);
                }
                board.undo();
            }
        }
        return newMove;
    }
    
    /**
     * This is the board being checked to see how undesirable
     * it is.
     * @param board is the board being checked.
     * @return returns the rating of the board.
     */
    public double rateBoard(Board board)
    {
        int[] boardHeights = board.getColumnHeights(); 
        double height = 0;
        for (int column = 0; column < boardHeights.length; column++)
        {
            height += boardHeights[column];
        }
        return height / board.getWidth();
    }
}
