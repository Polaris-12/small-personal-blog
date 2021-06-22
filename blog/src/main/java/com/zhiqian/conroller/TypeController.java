package com.zhiqian.conroller;

import com.zhiqian.pojo.Type;
import com.zhiqian.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("/toAddclassify")
    public String toAddClassifyPage(){
        return "admin/addclassify";
    }

    @GetMapping("/classify")
    public String list(Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        return "admin/classify";
    }

    @GetMapping("/editClassify")
    public String edit(Type type,Model model){
        model.addAttribute("type",type);
        return "admin/editclassify";
    }

    @PostMapping("/addClassify")
    public String add(@RequestParam("name") String name, RedirectAttributes attributes, Model model){
        //首先进行判断是否有同名分类
        Type type = typeService.getTypeByName(name);
        if (type==null) {
            int i = typeService.saveType(new Type(null, name,null));
            if (i>0){
                attributes.addFlashAttribute("message","操作成功！");
            }else {
                attributes.addFlashAttribute("message","操作失败！");
            }
            return "redirect:/admin/classify";
        }
        model.addAttribute("msg","该名称已存在");
        model.addAttribute("name",name);
        return "admin/addclassify";
    }

    @GetMapping("/deleteClassify")
    public String delete(@RequestParam("id") long id){
        typeService.deleteType(id);
        return "redirect:/admin/classify";
    }

    @PostMapping("/editClassify")
    public String update(Type type,RedirectAttributes attributes,Model model){
        //首先进行判断是否有同名分类
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName==null){
            int i = typeService.updateType(type);
            if (i>0){
                attributes.addFlashAttribute("message","操作成功！");
            }else {
                attributes.addFlashAttribute("message","操作失败！");
            }
            return "redirect:/admin/classify";
        }
        model.addAttribute("msg","该名称已存在");
        model.addAttribute("type",type);
        return "admin/editclassify";
    }
}
