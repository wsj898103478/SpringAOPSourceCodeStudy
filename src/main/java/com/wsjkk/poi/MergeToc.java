package com.wsjkk.poi;

import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.fields.TableOfContent;

public class MergeToc {
    public static void main(String[] args){

        //File path of the first document
        String filePath1 = "new.docx";
        //File path of the second document
        String filePath2 = "AddToc.docx";

        //Load the first document
        Document document = new Document(filePath1);

        //Insert content of the second document into the first document
        document.insertTextFromFile(filePath2, FileFormat.Docx_2013);

        //Save the resultant document
        TableOfContent toc = new TableOfContent(document, "{\\o \"2-3\" \\h \\z \\u}");
        document.updateTableOfContents();
        document.saveToFile("new.docx", FileFormat.Docx_2013);

    }
}
