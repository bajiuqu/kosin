package client;

import com.bajiuqu.common.vo.ResponseResult;
import dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RequestMapping("userController")
public interface Userclient {

    /**
     * 添加用户信息
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/insert")
    ResponseResult insert(@RequestBody UserDTO userDTO);

    /**
     * 获取所有用户: 如果有参数则根据参数获取
     *
     * @param userType 用户类型
     * @return 指定类型的所有用户
     */
    @GetMapping("/getUserAll")
    ResponseResult getUserAll(@RequestParam(name = "userType", required = false) String userType);

}
