import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pangff on 17/4/1.
 */
public class NewsWrapper {

    public List<News> news = new ArrayList<>();

    public static class News {

        public String id;

        public String type;

        public String title;

        public String image;

        public String text;
    }


    static int imageId = 0;

    public static List<News> init20News(int start) {
        List<News> list = new ArrayList();
        for (int i = start; i < start + 20; i++) {
            News news = new News();
            news.id = "sa1-s" + i;
            news.type = "text";
            if (i == start || i == (start + 6) || i == (start + 12) || i == (start + 19)) {
                news.type = "image-1";
                news.image = "http://onqln9zi5.bkt.clouddn.com/news/images/title_" + imageId + ".jpg";
                imageId++;
            } else {
                news.title = "尹蔚民：就业是最大的民生，也是经济发展的重要支撑。";
                news.text = "尹蔚民：就业是最大的民生，也是经济发展的重要支撑。近年来，在经济下行压力加大、产业结构调整不断深化的情况下，就业局势保持了总体稳定。城镇新增就业连续四年超过1300万人，城镇登记失业率、调查失业率都保持在比较低的水平。刚才我也讲到，这已经成为经济社会发展的一大亮点。\\r\\r    取得这样的成绩实属不易，这得益于党中央、国务院对就业工作的高度重视和坚强有力的领导，得益于全社会共同的支持和努力，也得益于劳动者积极的参与。\\r\\r    具体来讲，有这几个重要因素：第一，经济持续发展，是稳定和扩大就业的重要基础。虽然我国经济增速这几年在下降，但是仍然保持在合理区间，而且在全世界也是名列前茅。去年GDP增速是6.7%，经济总量超过74万亿。由于经济这个蛋糕做大了，所以就业的容量相应就扩大了。而且现在经济基数大了，增长一个百分点所对应的拉动就业的能力也比过去更加强了。十一五期间GDP增长一个点平均拉动就业100万人；十二五期间GDP增长一个点，平均拉动就业170万人；去年GDP增长一个点，拉动就业超过了190万人。我觉得，经济发展是稳定和扩大就业的重要前提。\\r\\r    第二，经济结构在调整，在优化。不同的经济结构对就业的带动能力、拉动能力是不同的，特别是第三产业对就业的拉动能力，平均要高出第二产业20%。去年第三产业占GDP的比重达到了51.6%，高出第二产业11.8个百分点。所以，经济结构优化，对于就业的拉动能力明显增强。\\r\\r    第三，改革持续释放红利。本届政府成立以来，持续转变政府职能，推进放管服改革，推进商事制度改革，推进营改增为重点的税收制度改革，同时大力倡导大众创业、万众创新。新的技术、新的业态、新的动能在不断增加，就业形态也是多元化，创业带动就业的倍增效应也不断显现。举一个例子，去年日均新增企业户数15000户，同比多增了3000户，就这一点，对于城镇新增就业的贡献率就达到了40%。\\r\\r    第四，积极的就业政策不断完善，公共就业服务体系也不断完善。我们经过这么多年的实践，中国特色的就业政策体系基本建立起来了，大力推行网上服务，公共服务体系从省到社区、乡镇，也都是比较完整的。\\r    综上所述，这些因素支撑了就业的发展，保持了就业局势的总体稳定。\\r\\r    当然，我们也要看到，今年的就业形势仍然复杂，就业任务仍然繁重。有两个突出的特点，一是总量压力仍然巨大，二是结构性矛盾也更加突出。我们将按照党中央、国务院的部署，采取各种综合性措施，我们有信心也有能力，保持今年就业局势的稳定。";
            }
            list.add(news);
        }
        return list;
    }


    public static void writeFile(String dir, String name, String content) {
        File file = new File(dir, name);
        try {
            file.createNewFile(); // 创建文件
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 向文件写入内容(输出流)
        String str = content;
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        try {
            FileOutputStream in = new FileOutputStream(file);
            try {
                in.write(bt, 0, bt.length);
                in.close();
                // boolean success=true;
                // System.out.println("写入文件成功");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void initData() {
        Gson gson = new Gson();
        for (int i = 0; i < 500; i++) {
            NewsWrapper newsWrapper =  new NewsWrapper();
            List<News> list = init20News(i * 20);
            newsWrapper.news = list;
            String content = gson.toJson(newsWrapper);
            String name = "";
            if (i == 0) {
                name = "init.json";
            } else {
                name = "infinite-end-" + (i-1) + ".json";
            }
            writeFile("./upload/news", name, content);
        }
    }

    public static void main(String[] args){
        initData();
    }
}
