package com.wsjkk.poi;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TableOfContent;
import com.spire.doc.fields.TextRange;

import java.awt.*;

public class AddTOC2 {
    public static void main (String[] args){
        //加载已设置大纲级别的测试文档
        Document doc = new Document("C:\\github\\richText2Docx.docx");

        //在文档最前面插入一个段落，写入文本并格式化
        Paragraph parainserted = new Paragraph(doc);
        TextRange tr= parainserted.appendText("目 录");
        System.out.println(tr.getText());
        tr.getCharacterFormat().setBold(true);
        tr.getCharacterFormat().setTextColor(Color.gray);
        doc.getSections().get(0).getParagraphs().insert(1,parainserted);
        parainserted.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //通过域代码添加目录表
        TableOfContent toc = new TableOfContent(doc, "{\\o \"1-3\" \\h \\z \\u}");
        doc.getSections().get(0).getParagraphs().get(1).appendTOC(2,3);
        doc.updateTableOfContents();

        //保存文档
        doc.saveToFile("richText2Docx.docx", FileFormat.Docx_2010);
    }
}