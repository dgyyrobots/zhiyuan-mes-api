package com.dofast.module.wms.service.gprinter;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@Validated
public class GprinterServiceImpl implements GprinterService {

    @Override
    public void printLabel(String labelContent) {
        try {
            // TSPL 指令，用于生成条码标签
            String tsplCommand = "SIZE 80 mm, 60 mm\n" +   // 设置标签大小
                    "GAP 3 mm, 0 mm\n" +       // 设置标签间距
                    "CLS\n" +                  // 清除打印缓冲区
                    "TEXT 100,100,\"TSS24.BF2\",0,1,1,\"" + labelContent + "\"\n" +
                    "BARCODE 100,200,\"128\",100,1,0,2,2,\"1234567890\"\n" +
                    "PRINT 1,1\n";             // 打印一张标签

            // 将指令转换为输入流
            InputStream inputStream = new ByteArrayInputStream(tsplCommand.getBytes());

            // 获取已安装的打印服务
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

            // 查找佳博打印机
            PrintService gprinterService = null;
            for (PrintService printService : printServices) {
                if (printService.getName().contains("Gprinter")) {
                    gprinterService = printService;
                    break;
                }
            }

            if (gprinterService != null) {
                // 创建打印作业
                DocPrintJob job = gprinterService.createPrintJob();
                Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
                // 执行打印
                job.print(doc, null);
                System.out.println("打印任务已发送到佳博打印机！");
            } else {
                System.out.println("未找到佳博打印机！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
