import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import student.tetris.*;
// -------------------------------------------------------------------------
/**
 *  This is a testclass that checks that all the methods in the
 *  CleverBrain class work as expected.
 *
 *  @author Nana Yaw Barimah Oteng
 *  @version (2022.03.31)
 */
public class CleverBrainTest
    extends TestCase
{
    //~ Fields ................................................................
    private CleverBrain myBrain;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new CleverBrainTest test object.
     */
    public CleverBrainTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        myBrain = new CleverBrain();
    }


    // ----------------------------------------------------------
    /**
     * This is an example test case to see if the rateboard() method
     * adds up all the heights of every column and divides 
     * it by the number of columns 
     * and returns this value as a double value.
     */
    public void testRateBoard()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            "#### #####",
            "##### ####" 
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(1.900, within(0.001));
    }
    
    /**
     * This is an example test case to see if and L will plug up a hole
     * when bestMove() is called. This is a testcase to ensure that a piece
     * that should block a hole, will move into that hole.
     */
    public void testBestMoveCheckHole()
    {
        Board board = new Board(10, 24,
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.RIGHT_L, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(4, 0));
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.RIGHT_L, 3));
    
    }
    
    /**
     * This is an example test case to see if an square will avoid a hole 
     * when bestMove() is called. This is a testcase to ensure that a piece
     * that should avoid a hole, will avoid that hole.
     */
    public void testBestMoveCheckSquare()
    {
        Board board = new Board(10, 24,
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.SQUARE, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(0, 1));
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.SQUARE, 3));
    
    }
    
    /**
     * This is an example test case to show that a piece wont't be placed if 
     * it is above the heightLimit, unless the board has no choice but to 
     * place it.
     */
    public void testBestMoveCheckHeightLimit()
    {
        Board board = new Board(10, 24,
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.SQUARE, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 0);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(null);
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.SQUARE, 3));
    
    }
}
