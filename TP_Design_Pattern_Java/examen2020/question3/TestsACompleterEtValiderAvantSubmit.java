package question3;


public class TestsACompleterEtValiderAvantSubmit extends junit.framework.TestCase{

     public void testAppelsDeLaMethode_m() throws Exception {
        try{
            A a = null; // à compléter
            a.m(0);
        }catch(Exception e){
            fail("aucune exception n'est attendue pour m(0)");
        }

        try{
            A a = null; // à compléter
            a.m(-10);
            fail("la pre-condition doit lever une exception pour m(-1)");
        }catch(Exception e){
            //verif(e);
            StackTraceElement st = e.getStackTrace()[0];
            assertEquals("Ce n'est pas une exception issue d'une méthode de la classe B ???","question3.B",st.getClassName());
            assertEquals("Ce n'est pas la pré-condition qui en est à l'origine ???","pre",st.getMethodName());
        }

        try{
            A a = null; // à compléter
            a.m(Integer.MAX_VALUE);
            fail("la post-condition doit lever une exception pour m(Integer.MAX_VALUE)");
        }catch(Exception e){
            //verif(e);
            StackTraceElement st = e.getStackTrace()[0];
            assertEquals("Ce n'est pas une exception issue d'une méthode de la classe B ???","question3.B",st.getClassName());
            assertEquals("Ce n'est pas la post-condition qui en est à l'origine ???","post",st.getMethodName());            
        }
    }

    private static void verif (Exception e){

        for( StackTraceElement st : e.getStackTrace()) {
            System.out.println("Class: " + st.getClassName() + " Method : " 
                +  st.getMethodName() + " line : " + st.getLineNumber());  
        }

    }
    
}