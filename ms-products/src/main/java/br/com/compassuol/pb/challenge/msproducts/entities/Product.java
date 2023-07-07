package br.com.compassuol.pb.challenge.msproducts.entities;

import br.com.compassuol.pb.challenge.msproducts.entities.dto.CategoryDto;
import br.com.compassuol.pb.challenge.msproducts.entities.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "tb_product",
        schema = "challenge"
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @Size(max = 128)
    @NotNull
    @Column(name = "description", nullable = false, length = 128)
    private String description;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 128)
    @NotNull
    @Column(name = "img_url", nullable = false, length = 128)
    private String imgUrl;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10)
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new LinkedHashSet<>();

    public Product() {
    }

    public Product(@NotNull Date date, @NotNull String description, @NotNull String name, @NotNull String imgUrl, @NotNull BigDecimal price, Set<Category> categories) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.categories = categories;
    }

    public Product(Long id, @NotNull Date date, @NotNull String description, @NotNull String name, @NotNull String imgUrl, @NotNull BigDecimal price, Set<Category> categories) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public ProductDto toDto() {
        return new ProductDto(
                this.date, this.description, this.name, this.imgUrl, this.price,
                this.categories.stream().map(category -> new CategoryDto(category.getId())).collect(Collectors.toSet())
        );
    }

}
