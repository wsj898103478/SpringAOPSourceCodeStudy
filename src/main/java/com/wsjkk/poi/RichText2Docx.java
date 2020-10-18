package com.wsjkk.poi;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

public class RichText2Docx {
    /**
     * 转化html为word并保存。
     *
     * @param data html
     */
    public String resolveHtml(String data) throws IOException {

        Document document = Jsoup.parse(data);
        //补全html标签。
        document.outputSettings().syntax(Document.OutputSettings.Syntax.html);
        document = Jsoup.parse(document.html());
        //获取完整的html
        String htmlStr = document.html();
        InputStream is = new ByteArrayInputStream(htmlStr.getBytes("UTF-8"));
        OutputStream oss = new FileOutputStream("C:\\github\\richText2Docx.docx");
        POIFSFileSystem fs = new POIFSFileSystem();
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(oss);
        oss.close();
        is.close();
        return "";
    }
}
