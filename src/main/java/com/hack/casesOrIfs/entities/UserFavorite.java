package com.hack.casesOrIfs.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user-favorite")
public class UserFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    private Date favoritedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Date getFavoritedAt() {
        return favoritedAt;
    }

    public void setFavoritedAt(Date favoritedAt) {
        this.favoritedAt = favoritedAt;
    }
}