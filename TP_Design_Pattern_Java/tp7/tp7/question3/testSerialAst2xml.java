package question3;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
import question2.*;
import java.util.Random;
import java.io.*;

public class testSerialAst2xml extends junit.framework.TestCase{

    
  public void test_serialAst2xml(){
    String nomDuFichier = "test_Ast2xml"+ new Random().nextInt(100000)  + ".xml";
    try{
    SerialiseDeserialiseAST_XML.serialAst2xml(new AST_Fact(10),nomDuFichier);
    
    File file = new File(nomDuFichier);
    BufferedReader in = new BufferedReader(new FileReader(file));
      try{
        in.readLine(); //<?xml version="1.0" encoding="UTF-8"?>
        assertTrue(" <programme> est attendu ? ",in.readLine().trim().endsWith("<programme>"));
        assertTrue(" <Sequence> est attendu ? ",in.readLine().trim().endsWith("<Sequence>"));
        assertTrue(" mauvaise balise ? ",in.readLine().trim().endsWith("<Affectation>"));
        assertTrue(" mauvaise balise ? ",in.readLine().trim().endsWith("</Variable>"));
      }catch(Exception e){
        throw e;
      }finally{
        in.close();
        file.delete();
      }
    }catch (Exception e){
     fail("exception inattendue !!! : " + e.getMessage()); 
   }
  }
  
}
