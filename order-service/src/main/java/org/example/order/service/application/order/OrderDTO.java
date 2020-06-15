package org.example.order.service.application.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderDTO extends RepresentationModel<OrderDTO> {

    public OrderDTO(String id, String clientId,
                    String productId, String status) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.status = status;
    }

    private String id;
    @NotEmpty
    private String clientId;
    @NotEmpty
    private String productId;
    private String status;
    private String paymentId;

}
