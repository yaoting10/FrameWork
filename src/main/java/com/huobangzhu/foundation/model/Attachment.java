package com.huobangzhu.foundation.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: Tim.Yao
 */
@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Attachment implements Serializable{

    private String label;

    private String description;

    private String name;
}
