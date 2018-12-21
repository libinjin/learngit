package com.youguu.quant.util;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.signal.pojo.TradeSignal;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by SomeBody on 2016/8/23.
 */
public class FileUtil {
    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_SERVER);


    public static File createFile(String dirPath) throws IOException {
        File file = new File(dirPath);
        // 创建目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //创建文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * @param fileName
     * @param signal
     */
    public static void write(String fileName, TradeSignal signal) {
        try {
            File file = createFile(fileName);

            OutputStream os = new FileOutputStream(file, true);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            dos.writeInt(signal.getMarketId());
            dos.writeInt(Integer.parseInt(signal.getStockcode()));
            dos.writeLong(signal.getDatetime());
            dos.writeInt(signal.getPrice());
            dos.writeInt(signal.getCloseprice());
            dos.writeByte((byte) signal.getDirect().charAt(0));

            dos.flush();

            os.close();
            bos.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 覆盖偏移N个对象以后的那个对象
     *
     * @param fileName
     * @param signal
     */
    public static void writeLast(String fileName, TradeSignal signal, int i) {
        try {
            File file = createFile(fileName);

            RandomAccessFile rf = new RandomAccessFile(file, "rwd");

            int sumSize = (int) (file.length() / TradeSignal.LENGTH);

            rf.seek(TradeSignal.LENGTH * (sumSize - i));
            rf.writeInt(signal.getMarketId());
            rf.writeInt(Integer.parseInt(signal.getStockcode()));
            rf.writeLong(signal.getDatetime());
            rf.writeInt(signal.getPrice());
            rf.writeInt(signal.getCloseprice());
            rf.writeByte((byte) signal.getDirect().charAt(0));

            rf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param fileName 文件路径+文件名称 格式:基本路径/{策略ID}/{股票代码}.dat
     * @param position 读取的起始位置
     * @param length   每次读取的字节长度,length为-1时代表从position位置读取到文件末尾
     * @return
     * @throws Exception
     */
    public static List<TradeSignal> read(String fileName, long position, long length) {
        try {
            List<TradeSignal> list = new ArrayList<>();
            InputStream instream = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(instream);

            int available = dis.available();//文件总长度
            long willLen = 0;//将要读取的数据长度
            if (length == -1) {
                willLen = available - position;
            } else {
                willLen = length;
            }

            dis.skip(position);

            long readLen = 0;//已读取字节长度
            while (available != 0) {
                int marketId = dis.readInt();
                int stockcode = dis.readInt();
                long datetime = dis.readLong();
                int price = dis.readInt();
                int closeprice = dis.readInt();
                byte direct = dis.readByte();

                TradeSignal ts = new TradeSignal();
                ts.setMarketId(marketId);
                ts.setStockcode(String.valueOf(stockcode));
                ts.setDatetime(datetime);
                ts.setPrice(price);
                ts.setCloseprice(closeprice);
                ts.setDirect(new String(new byte[]{direct}, "UTF-8"));
                list.add(ts);
                readLen += TradeSignal.LENGTH;
                if (readLen >= willLen || (readLen + position) >= available) {
                    break;
                }
            }

            instream.close();
            dis.close();
            return list;
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException: " + fileName, e);
        } catch (IOException e) {
            logger.error("IOException: " + fileName, e);
        }
        return null;
    }

    /**
     * 每次读一个信号返回
     */
    public static TradeSignal readOneTradeSignal(String fileName, long position) {
        try {
            List<TradeSignal> list = new ArrayList<>();
            InputStream instream = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(instream);

            int available = dis.available();//文件总长度
            if (available < TradeSignal.LENGTH || (position + TradeSignal.LENGTH) > available) {
                return null;
            }

            dis.skip(position);

            int marketId = dis.readInt();
            int stockcode = dis.readInt();
            long datetime = dis.readLong();
            int price = dis.readInt();
            int closeprice = dis.readInt();
            byte direct = dis.readByte();

            TradeSignal ts = new TradeSignal();
            ts.setMarketId(marketId);
            ts.setStockcode(String.valueOf(stockcode));
            ts.setDatetime(datetime);
            ts.setPrice(price);
            ts.setCloseprice(closeprice);
            ts.setDirect(new String(new byte[]{direct}, "UTF-8"));

            instream.close();
            dis.close();
            return ts;
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException: " + fileName, e);
        } catch (IOException e) {
            logger.error("IOException: " + fileName, e);
        }
        return null;
    }

    public static List<TradeSignal> readInterval(String fileName, long startDate, long endDate) {
        try {
            List<TradeSignal> list = new ArrayList<>();
            File file = new File(fileName);
            long available = file.length();//文件总长度

            long readLen = 0;//已读取字节长度
            ByteBuffer buffer = ByteBuffer.allocate(TradeSignal.LENGTH);
            FileInputStream fis = new FileInputStream(file);
            FileChannel ch = fis.getChannel();

            while (available != 0) {
                if (available - readLen <= 0) {
                    break;//已读到文件头，跳出
                }

                ch.position(available - readLen - TradeSignal.LENGTH);
                ch.read(buffer);
                buffer.flip();

                readLen += TradeSignal.LENGTH;//累加已读取长度

                int marketId = buffer.getInt();
                int stockcode = buffer.getInt();
                long datetime = buffer.getLong();
                int price = buffer.getInt();
                int closeprice = buffer.getInt();
                String direct = new String(new byte[]{buffer.get()}, "UTF-8");
                buffer.clear();

                if (datetime/10000 < startDate) {
                    break;
                }

                if (datetime/10000 > endDate) {
                    continue;
                }

                TradeSignal ts = new TradeSignal();
                ts.setMarketId(marketId);
                ts.setStockcode(String.valueOf(stockcode));
                ts.setDatetime(datetime);
                ts.setPrice(price);
                ts.setCloseprice(closeprice);
                ts.setDirect(direct);
                list.add(ts);

            }

            ch.close();
            fis.close();
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param fileName 文件路径+文件名称 格式:基本路径/{策略ID}/{股票代码}.dat
     * @return
     * @throws Exception
     */
    public static List<TradeSignal> read(String fileName, int pageIndex, int pageSize, boolean isContainH, int endDate) throws Exception {

        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");

        List<TradeSignal> list = new ArrayList<>();
        File file = new File(fileName);
        long available = file.length();//文件总长度

        long readLen = 0;//已读取字节长度
        int rowCount = 0;//已读取有效记录数
        ByteBuffer buffer = ByteBuffer.allocate(TradeSignal.LENGTH);
        FileInputStream fis = new FileInputStream(file);
        FileChannel ch = fis.getChannel();

        while (available != 0) {
            if (available - readLen <= 0) {
                break;//已读到文件头，跳出
            }

            ch.position(available - readLen - TradeSignal.LENGTH);
            ch.read(buffer);
            buffer.flip();

            int marketId = buffer.getInt();
            int stockcode = buffer.getInt();
            long datetime = buffer.getLong();
            int price = buffer.getInt();
            int closeprice = buffer.getInt();
            String direct = new String(new byte[]{buffer.get()}, "UTF-8");
            buffer.clear();

            readLen += TradeSignal.LENGTH;//累加已读取长度


            if (!isContainH && "H".equals(direct)) {
                continue;
            }

            if ("".equals(String.valueOf(datetime))) {
                continue;
            }

            String tradeTimeStr = "";
            try {
                Date tradeTime = yyyyMMddHHmm.parse(String.valueOf(datetime));
                tradeTimeStr = yyyyMMdd.format(tradeTime);
                int tempDate = Integer.parseInt(tradeTimeStr);
                if (pageIndex == 1 && tempDate > endDate) {
                    continue;
                }
            } catch (Exception e) {
                logger.error("FileUtil read error stockcode=" + stockcode + ",datetime=" + datetime + ",tradeTimeStr=" + tradeTimeStr + ",exception=" + e.getMessage());
            }


            rowCount++;

            if (rowCount <= pageIndex * pageSize) {
                TradeSignal ts = new TradeSignal();
                ts.setMarketId(marketId);
                ts.setStockcode(String.valueOf(stockcode));
                ts.setDatetime(datetime);
                ts.setPrice(price);
                ts.setCloseprice(closeprice);
                ts.setDirect(direct);
                list.add(ts);
            } else {
                continue;
            }

            if (rowCount == pageIndex * pageSize) {
                break;//读到分页需要的条数跳出循环
            }

        }

        ch.close();
        fis.close();
        return list;
    }

    /**
     * 读取最后一条记录
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static TradeSignal read(String fileName) throws Exception {
        InputStream instream = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(instream);
        int available = dis.available();//文件总长度
        long willLen = TradeSignal.LENGTH;//将要读取的数据长度
        dis.skip(available - willLen);

        int marketId = dis.readInt();
        int stockcode = dis.readInt();
        long datetime = dis.readLong();
        int price = dis.readInt();
        int closeprice = dis.readInt();
        byte direct = dis.readByte();

        TradeSignal ts = new TradeSignal();
        ts.setMarketId(marketId);
        ts.setStockcode(String.valueOf(stockcode));
        ts.setDatetime(datetime);
        ts.setPrice(price);
        ts.setCloseprice(closeprice);
        ts.setDirect(new String(new byte[]{direct}, "UTF-8"));


        instream.close();
        dis.close();
        return ts;
    }

    /**
     * 读取目录下所有文件名返回
     *
     * @param path
     * @return
     */
    public static List<String> readFileName(String path) {
        List<String> fileNameList = new ArrayList<>();

        File file = new File(path);
        if(file != null){
            File[] array = file.listFiles();
            if(array!=null){
                for (int i = 0; i < array.length; i++) {
                    if (array[i].isFile() && !array[i].getName().contains("bat")) {
                        fileNameList.add(array[i].getName().substring(0, array[i].getName().indexOf(".")));
                    }
                }
            } else {
                logger.debug("目录{}无文件", path);
            }

        } else {
            logger.debug("目录{}无文件", path);
        }

        return fileNameList;
    }

    /**
     * 获取文件长度
     *
     * @param path 路径+文件名
     * @return
     */
    public static int getFileLength(String path) {
        int available = 0;//文件总长度
        try {
            InputStream instream = new FileInputStream(path);
            DataInputStream dis = new DataInputStream(instream);
            available = dis.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return available;
    }


    public static void print(String fileName) throws Exception {
        File file = new File(fileName);
        long available = file.length();//文件总长度

        long readLen = 0;//已读取字节长度
        ByteBuffer buffer = ByteBuffer.allocate(TradeSignal.LENGTH);
        FileInputStream fis = new FileInputStream(file);
        FileChannel ch = fis.getChannel();

        while (available != 0) {
            if (available - readLen - TradeSignal.LENGTH < 0) {
                continue;
            }
            ch.position(available - readLen - TradeSignal.LENGTH);
            ch.read(buffer);
            buffer.flip();

            int marketId = buffer.getInt();
            int stockcode = buffer.getInt();
            long datetime = buffer.getLong();
            int price = buffer.getInt();
            int closeprice = buffer.getInt();
            String direct = new String(new byte[]{buffer.get()}, "UTF-8");
            buffer.clear();

            readLen += TradeSignal.LENGTH;//累加已读取长度
            if (available - readLen <= 0) {
                break;//已读到文件头，跳出
            }

        }

        ch.close();
        fis.close();
    }

    /**
     * 删除文件
     *
     * @param dir
     * @return
     */
    public static int deleteFile(String dir) {
        int fileNum = 0;
        try {
            File f = new File(dir);//定义文件路径
            if (f.exists() && f.isDirectory()) {//判断是文件还是目录
                if (f.listFiles().length == 0) {//若目录下没有文件则直接删除
                    f.delete();
                } else {//若有则把文件放进数组，并判断是否有下级目录
                    File delFile[] = f.listFiles();
                    int i = f.listFiles().length;
                    for (int j = 0; j < i; j++) {
                        if (delFile[j].isDirectory()) {
                            deleteFile(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径
                        }
                        delFile[j].delete();//删除文件
                        fileNum++;
                    }
                }
            }
            return fileNum;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 判断策略是否有数据文件
     * @param strategyId
     * @return
     */
    public static boolean isExistsStrategyFile(int strategyId){
        try {
            String hisOriginFilePath = QuantTradePlateConfig.getInstance().getHisOriginFilePath();//回测目录
            String filterPath = QuantTradePlateConfig.getInstance().getStockFilePath();//回测+实时(信号过滤后目录)

            String hisSrcDir = hisOriginFilePath + File.separator + strategyId;
            String filterSrcDir = filterPath + File.separator + strategyId;
            //存在一个目录则视为存在策略数据
            File hisFile = new File(hisSrcDir);
            File filterFile = new File(filterSrcDir);

            if(!hisFile.exists() && !filterFile.exists()){
                return false;
            } else {
                if(hisFile.exists()){
                    File[] hisFileList = hisFile.listFiles();
                    if(hisFileList!=null && hisFileList.length>0){
                        return true;
                    }
                }
                if(filterFile.exists()){
                    File[] filterFileList = filterFile.listFiles();
                    if(filterFileList!=null && filterFileList.length>0){
                        return true;
                    }
                }
            }

        } catch (Exception e){
            logger.error("isExistsStrategyFile error", e);
        }
        return false;
    }


    /**
     * 回测备份，每次需要重新回测某策略时需要先进行数据备份
     *
     * @param strategyId 策略ID
     * @return
     */
    public static boolean backupWhenLoop(int strategyId) {

        //备份日期，格式yyyyMMdd
        Calendar cal = Calendar.getInstance();
        int backupDate = cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DATE);

        try {
            String hisOriginFilePath = QuantTradePlateConfig.getInstance().getHisOriginFilePath();//回测目录
            String filterPath = QuantTradePlateConfig.getInstance().getStockFilePath();//回测+实时(信号过滤后目录)

            //1、备份上一次回测数据，目录/data/origin/his/{strategyId}
            String hisSrcDir = hisOriginFilePath + File.separator + strategyId;
            String hisDestDir = hisOriginFilePath + File.separator + "backup" + File.separator + backupDate + File.separator + strategyId;
            copyDirectiory(hisSrcDir, hisDestDir);

            //2、备份过滤后的回测+实时数据，目录/data/quant/{strategyId}
            String filterSrcDir = filterPath + File.separator + strategyId;
            String filterDestDir = filterPath + File.separator + "backup" + File.separator + backupDate + File.separator + strategyId;
            copyDirectiory(filterSrcDir, filterDestDir);

            //3、删除目录及文件
            deleteFile(hisSrcDir);
            deleteFile(filterSrcDir);
            return true;
        } catch (Exception e){
            logger.error("backupWhenLoop error", e);
        }

        return false;
    }

    /**
     * 每天收盘后自动备份过滤后的回测+实时数据，然后执行清算，如清算失败，可以回滚数据
     *
     * @param strategyId 策略ID
     * @return
     */
    public static boolean backupWhenClose(int strategyId) {
        //备份日期，格式yyyyMMdd
        Calendar cal = Calendar.getInstance();
        int backupDate = cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DATE);

        try {
            String filterPath = QuantTradePlateConfig.getInstance().getStockFilePath();//回测+实时(信号过滤后目录)
            String filterSrcDir = filterPath + File.separator + strategyId;
            String filterDestDir = filterPath + File.separator + "backup" + File.separator + backupDate + File.separator + strategyId;
            copyDirectiory(filterSrcDir, filterDestDir);
            return true;
        } catch (Exception e){
            logger.error("backupWhenClose error", e);
        }

        return false;
    }

    /**
     * 解析“dat”文件，将解析后的数据写入csv文件，测试比对使用
     * @param path 存放解析文件的目录
     * @param strategyId 策略ID
     * @param stockCode 股票代码
     * @param signalList 信号列表
     * @return
     */
    public static boolean writeCsv(String path, int strategyId, String stockCode, List<TradeSignal> signalList) {
        boolean isSucess=false;
        String parsePath = path;
        parsePath = parsePath + File.separator + strategyId + File.separator + stockCode + ".csv";

        try {
            File newFile = FileUtil.createFile(parsePath);
            newFile.createNewFile();
        }catch (Exception e){

        }

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(new File(parsePath));
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(signalList!=null && !signalList.isEmpty()){
                for(TradeSignal tradeSignal : signalList){
                    StringBuffer sb = new StringBuffer();
                    String row = sb.append(String.valueOf(tradeSignal.getMarketId())).append(",")
                            .append(tradeSignal.getStockcode()).append(",")
                            .append(String.valueOf(tradeSignal.getPrice())).append(",")
                            .append(tradeSignal.getDirect()).append(",")
                            .append(String.valueOf(tradeSignal.getDatetime())).append(",")
                            .append(String.valueOf(tradeSignal.getCloseprice())).toString();
                    bw.write(row);
                    bw.newLine();
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }


    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        //创建备份目录
        File dirFile = new File(targetDir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

        //获取源文件夹所有文件和目录列表
        File[] fileList = (new File(sourceDir)).listFiles();
        if(fileList==null || fileList.length==0){
            return;
        }
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                File sourceFile = fileList[i];
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + fileList[i].getName());
                FileUtils.copyFile(sourceFile, targetFile);
            }

            //如果是目录，递归复制
            if (fileList[i].isDirectory()) {
                String dir1 = sourceDir + fileList[i].getName();
                String dir2 = targetDir + "/" + fileList[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     *查询指定日期之前的最后一个信号
     * @param fileName
     * @param date
     * @return
     */
    public static TradeSignal readByTime(String fileName, int date) {
        FileChannel ch = null;
        FileInputStream fis = null;
        try {
            List<TradeSignal> list = new ArrayList<>();
            File file = new File(fileName);
            long available = file.length();//文件总长度

            long readLen = 0;//已读取字节长度
            ByteBuffer buffer = ByteBuffer.allocate(TradeSignal.LENGTH);
            fis = new FileInputStream(file);
            ch = fis.getChannel();

            while (available != 0) {
                if (available - readLen <= 0) {
                    break;//已读到文件头，跳出
                }

                ch.position(available - readLen - TradeSignal.LENGTH);
                ch.read(buffer);
                buffer.flip();

                readLen += TradeSignal.LENGTH;//累加已读取长度

                int marketId = buffer.getInt();
                int stockcode = buffer.getInt();
                long datetime = buffer.getLong();
                int price = buffer.getInt();
                int closeprice = buffer.getInt();
                String direct = new String(new byte[]{buffer.get()}, "UTF-8");
                buffer.clear();

                if (datetime/10000 > date) {
                    continue;
                }

                TradeSignal ts = new TradeSignal();
                ts.setMarketId(marketId);
                ts.setStockcode(String.valueOf(stockcode));
                ts.setDatetime(datetime);
                ts.setPrice(price);
                ts.setCloseprice(closeprice);
                ts.setDirect(direct);
                return ts;

            }


        } catch (Exception e) {

        }finally {
            if(fis!=null){try{fis.close();}catch(Exception e){}}
            if(ch!=null){try{ch.close();}catch(Exception e){}}
        }
        return null;
    }



    /**
     * 查询指定日期之前的最后一个买入
     * @param fileName
     * @param date
     * @return
     */
    public static TradeSignal readLastBuyByTime(String fileName, int date) {
        FileChannel ch = null;
        FileInputStream fis = null;
        try {
            List<TradeSignal> list = new ArrayList<>();
            File file = new File(fileName);
            long available = file.length();//文件总长度

            long readLen = 0;//已读取字节长度
            ByteBuffer buffer = ByteBuffer.allocate(TradeSignal.LENGTH);
            fis = new FileInputStream(file);
            ch = fis.getChannel();

            while (available != 0) {
                if (available - readLen <= 0) {
                    break;//已读到文件头，跳出
                }

                ch.position(available - readLen - TradeSignal.LENGTH);
                ch.read(buffer);
                buffer.flip();

                readLen += TradeSignal.LENGTH;//累加已读取长度

                int marketId = buffer.getInt();
                int stockcode = buffer.getInt();
                long datetime = buffer.getLong();
                int price = buffer.getInt();
                int closeprice = buffer.getInt();
                String direct = new String(new byte[]{buffer.get()}, "UTF-8");
                buffer.clear();

                if (datetime/10000 >= date) {
                    continue;
                }
                if(!"B".equals(direct)){
                    continue;
                }

                TradeSignal ts = new TradeSignal();
                ts.setMarketId(marketId);
                ts.setStockcode(String.valueOf(stockcode));
                ts.setDatetime(datetime);
                ts.setPrice(price);
                ts.setCloseprice(closeprice);
                ts.setDirect(direct);
                return ts;

            }


        } catch (Exception e) {

        }finally {
            if(fis!=null){try{fis.close();}catch(Exception e){}}
            if(ch!=null){try{ch.close();}catch(Exception e){}}
        }
        return null;
    }
}
