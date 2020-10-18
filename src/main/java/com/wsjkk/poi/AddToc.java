package com.wsjkk.poi;

import com.spire.doc.*;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.TextRange;
import java.awt.*;

public class AddToc {
    public static void main(String[]args){
        //加载测试文档
        Document doc = new Document("richText2Docx.docx");

        //在文档最前面插入一个段落，写入文本并格式化
        Paragraph parainserted = new Paragraph(doc);
        TextRange tr= parainserted.appendText("目 录");
        tr.getCharacterFormat().setBold(true);
        tr.getCharacterFormat().setTextColor(Color.gray);
        doc.getSections().get(0).getParagraphs().insert(0,parainserted);
        parainserted.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //设置文档中指定段落的大纲级别
        doc.getSections().get(0).getParagraphs().get(2).applyStyle(BuiltinStyle.Heading_1);
        doc.getSections().get(0).getParagraphs().get(3).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(5).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(7).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(13).applyStyle(BuiltinStyle.Heading_2);
        doc.getSections().get(0).getParagraphs().get(14).applyStyle(BuiltinStyle.Heading_3);
        doc.getSections().get(0).getParagraphs().get(15).applyStyle(BuiltinStyle.Heading_3);

        //添加目录
        SectionCollection sections = doc.getSections();
        doc.getSections().get(0).getParagraphs().get(0).appendTOC(2,3);

        //更新目录表
        doc.updateTableOfContents();

        //保存文档
        doc.saveToFile("richText2Docx.docx",FileFormat.Docx_2010);
    }
}