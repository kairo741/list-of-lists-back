package com.example.kairo.listoflistsback.filter;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter extends FilterGeneral {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
}
