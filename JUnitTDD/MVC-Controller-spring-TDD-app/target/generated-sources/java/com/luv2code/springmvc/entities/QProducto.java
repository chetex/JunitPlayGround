package com.luv2code.springmvc.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProducto is a Querydsl query type for Producto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducto extends EntityPathBase<Producto> {

    private static final long serialVersionUID = 845687197L;

    public static final QProducto producto = new QProducto("producto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Double> precio = createNumber("precio", Double.class);

    public QProducto(String variable) {
        super(Producto.class, forVariable(variable));
    }

    public QProducto(Path<? extends Producto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProducto(PathMetadata metadata) {
        super(Producto.class, metadata);
    }

}

