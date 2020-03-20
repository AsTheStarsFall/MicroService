package com.tianhy.springbootspringmvc.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Data
public class ValidatorPojo {

    @NotNull(message = "ID不能为空")
    private Long id;

    @Future(message = "只能是将来日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @NotNull
    @DecimalMin("0.1")
    @DecimalMax(value = "10000.00")
    private double doubleValue;

    @NotNull
    @Min(value = 1, message = "最小值1")
    @Max(value = 88, message = "最大值88")
    private Integer integer;

    @Range(min = 1, max = 888, message = "范围1~888")
    private Long range;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度20~30")
    private String size;


}
