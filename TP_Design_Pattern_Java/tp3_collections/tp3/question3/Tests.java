package question3;
import java.util.Set;

public class Tests extends junit.framework.TestCase
{
    public void test1( question3.Factory<Set> f ) throws Exception
    {
        Set<Integer> set = f.create();
        for ( int i=20; i>0; i-- )
            set.add( i );
    } // test1(.)

    public void test_Creation()
    {
        try {
            test1( new TreeSetFactory() );
            test1( new HashSetFactory() );
        } catch( NoSuchMethodError e ) {
            fail( "NoSuchMethodError : " + e );
        } catch( Exception e ) {
            fail( " exception inattendue : " + e );
        } 
    } // test_Creation()
} // Tests
