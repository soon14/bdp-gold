package com.platform.mobile.controller;

import com.platform.bean.entity.shop.Cart;
import com.platform.bean.vo.front.Rets;
import com.platform.bean.vo.query.SearchFilter;
import com.platform.bean.vo.shop.CartVo;
import com.platform.service.shop.CartService;
import com.platform.utils.HttpUtil;
import com.platform.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author wlhbdp
 * @date ：Created in 11/5/2020 7:36 PM
 */
@RestController
@RequestMapping("/user/cart")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;
    @RequestMapping(value = "/queryByUser",method = RequestMethod.GET)
    public Object getByUser(){
        Long idUser = getIdUser(HttpUtil.getRequest());
        List<Cart> list = cartService.queryAll(SearchFilter.build("idUser", SearchFilter.Operator.EQ,idUser));
        return Rets.success(list);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object add(@RequestBody CartVo cartVo){

        Long idUser = getIdUser();
        cartVo.setIdUser(idUser);
       Integer result = cartService.add(cartVo);
        return Rets.success(result);
    }
    @RequestMapping(value="/count",method = RequestMethod.GET)
    public Object count(){
        Long idUser = getIdUser(HttpUtil.getRequest());
        List<Cart> list = cartService.queryAll(SearchFilter.build("idUser", SearchFilter.Operator.EQ,idUser));
        return Rets.success(list.size());
    }

    @RequestMapping(value = "/update/{id}/{count}",method = RequestMethod.POST)
    public Object update(@PathVariable("id") Long id,
                          @PathVariable("count") String count){
        Cart cart = cartService.get(id);
        cart.setCount(new BigDecimal(count));
        CartVo cartVo = new CartVo();
        cartVo.setIdUser(cart.getIdUser());
        //修改订单中的数量的时候统一走cartService.add方法以便进行库存数量判断，避免超卖，
        cartVo.setCount(Integer.valueOf(count)-cart.getCount().intValue());
        cartVo.setIdGoods(cart.getIdGoods());
        cartVo.setIdSku(cart.getIdSku());
        cartService.add(cartVo);
        return Rets.success();
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Object remove(@RequestBody Set<Long> ids){
        Long idUser = getIdUser();
        for(Long id:ids){
            Cart cart = cartService.get(id);
            if(cart.getIdUser().intValue() == idUser.intValue()){
                cartService.delete(cart);
            }
        }

        return Rets.success();
    }
}
