package com.cucci.common.form;

import com.cucci.common.base.SaveForm;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 系统参数保存表单
 *
 * @author shenyw
 **/
@Data
public class CodeSaveForm implements SaveForm {
    private static final long serialVersionUID = 5659278214694741882L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 年龄
     */
    @Max(value = 200, message = "年龄最大值不能超过200")
    @Min(value = 18, message = "年龄最小值不能小于18")
    private Integer age;

    /**
     * 地址
     */
    @Size(max = 100, message = "地址长度100个字符")
    @NotBlank(message = "地址不能为空")
    private String addr;
}
