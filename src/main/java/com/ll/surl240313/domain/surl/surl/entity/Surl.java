package com.ll.surl240313.domain.surl.surl.entity;

import com.ll.surl240313.domain.member.member.entity.Member;
import com.ll.surl240313.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Surl extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member author;

    private String url;

    private String title;
}