package com.neu.framework.client;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.neu.system.domain.PaymentModel;
import com.neu.system.domain.PaymentRefundModel;
import com.neu.system.domain.PaymentResponse;

import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 支付Client
 *
 * @date 2023/05/10
 */
public class PaymentClient {

    /**
     * 预付款
     *
     * @param model 付款信息
     * @return 付款账单流水号、付款二维码
     */
    public static PaymentResponse preCreate(PaymentModel model) {
        PaymentResponse response = new PaymentResponse();
        // 付款账单流水号
        String tradeNo = IdUtil.getSnowflake(1, 1).nextIdStr();
        response.setTradeNo(tradeNo);
        QrConfig config = new QrConfig(300, 300);
        if (model.getPaymentMethod() != null) {
            try {
                //String localPath = NeuConfig.getProfile();

                if (model.getPaymentMethod() == 1) {
                    ClassPathResource classPathResource = new ClassPathResource("/images/wx_logo_samll.png");
                    config.setImg(classPathResource.getAbsolutePath());
                } else if (model.getPaymentMethod() == 2) {
                    ClassPathResource classPathResource = new ClassPathResource("/images/ali_logo_small.png");
                    config.setImg(classPathResource.getAbsolutePath());
                }
            } catch (Exception e) {
            }
        }
        byte[] buffer = QrCodeUtil.generatePng("支付", config);
        Encoder encode = Base64.getEncoder();
        String qrCode = encode.encodeToString(buffer);
        response.setQrCode(qrCode);
        return response;
    }

    /**
     * 付款
     *
     * @param model 付款参数
     * @return 成功、失败
     */
    public static PaymentResponse create(PaymentModel model) {
        PaymentResponse response = new PaymentResponse();
        int random = (int) (Math.random() * 10 + 1);
        if (random < 3) {
            PaymentResponse response1 = response.error("支付失败");
            response1.setTradeNo(IdUtil.objectId());
            return response1;
        } else {
            return response.success(IdUtil.objectId(), "");
        }
    }

    /**
     * 退款
     *
     * @param param 退款参数
     * @return 成功（退款账单流水号）、失败
     */
    public static PaymentResponse refund(PaymentRefundModel param) {
        PaymentResponse response = new PaymentResponse();
        int random = (int) (Math.random() * 10 + 1);
        if (random < 2) {
            return response.error("退款失败");
        } else {
            // 退款账单流水号
            String tradeNo = IdUtil.getSnowflake(1, 1).nextIdStr();
            return response.success(tradeNo, null);
        }
    }

    public static void main(String[] args) {
        PaymentModel model = new PaymentModel();
        model.setPaymentMethod(1);
        PaymentClient.preCreate(model).getQrCode();
        PaymentClient.create(model);
        PaymentRefundModel refund = new PaymentRefundModel();
        PaymentClient.refund(refund);
    }

}
