package com.cky.community.cache;
import com.cky.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("编程语言");
        program.setTags(Arrays.asList("c", "c++",  "java", "python", "golang", "c#", "javascript", "php", "shell",  "swift",  "ruby", "rust", "perl" , "objective-c", "lua", "scala", "node.js", "asp.net"));
        tagDTOS.add(program);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server","nginx"));
        tagDTOS.add(server);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "django", "flask", "tornado", "express", "struts" ));
        tagDTOS.add(framework);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("idea", "eclipse", "Pycharm", "clion", "visual-studio",  "visual-studio-code", "vim", "sublime-text"));
        tagDTOS.add(tool);

        TagDTO humanities = new TagDTO();
        humanities.setCategoryName("人文科学");
        humanities.setTags(Arrays.asList("历史","地理","经济学","社会学","心理学","艺术","哲学","政治学","管理学","法学"));
        tagDTOS.add(humanities);

        TagDTO science = new TagDTO();
        science.setCategoryName("自然科学");
        science.setTags(Arrays.asList("数学","物理","化学","生物","天文"));
        tagDTOS.add(science);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, " ");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(" "));
        return invalid;
    }

    public static void main(String[] args) {
        int i = (5 - 1) >>> 1;
        System.out.println(i);
    }
}
