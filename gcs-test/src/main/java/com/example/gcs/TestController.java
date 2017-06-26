package com.example.gcs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created in gcs-fs on 6/25/17.
 */
@Controller
public class TestController {


  @RequestMapping(value = "/ls", method = RequestMethod.GET)
  @ResponseBody
  public List<ListItem> geList() {
    List<ListItem> list = new ArrayList();
    File dir = new File("/gcs/app_folder");
    if(dir.exists() && dir.isDirectory()) {
      File[] files = dir.listFiles();
      for (File file : files) {
        ListItem item = new ListItem();
        item.setFileName(file.getName());
        item.setPath(file.getAbsolutePath());
        item.setIsDirectory(file.isDirectory());
        list.add(item);
      }
    }
    else {
      ListItem item = new ListItem();
      item.setFileName("no file found");
      item.setDirectory(false);
      item.setPath("/");
    }

    return list;
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,String> getTest() {
    Map<String,String> map = new HashMap<>();
    map.put("time", "" + System.currentTimeMillis());
    return map;
  }

}
