package com.springApplication.moduloProduccion.views;


import com.springApplication.moduloProduccion.models.Productos;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.Serializable;

@Route(value = "explosionmateriales", layout = MainLayout.class)
@PageTitle(value = "Boom de Materiales")
public class BoomMaterialesView extends VerticalLayout{

    private ComboBox<Productos> seleccionProductos;
    private TextField codigoProducto;
    private TextField ingresarMateriales;
    private Button botonEliminarMaterial;
    private Button botonAniadirMaterial;
    private Button[] botonesCRUD;
    private Tab altaProductos;
    private Tab listaProductos;
    private Grid<Productos> tablaProductos;
    private HorizontalLayout panelProductos = new HorizontalLayout();
    private FormLayout panelProcesosYBotones = new FormLayout();
    private FormLayout panelNuevosProcesos = new FormLayout();
    private HorizontalLayout panelBoronesCRUD = new HorizontalLayout();
    private VerticalLayout panelContenidoAltaMateriales = new VerticalLayout();
    private VerticalLayout panelContenidoTabla = new VerticalLayout();
    private Tabs menu = new Tabs();


    public BoomMaterialesView() {

        initComponents();

    }

    private void initComponents(){

        seleccionProductos = new ComboBox<>("Selección de productos");
        codigoProducto = new TextField("Código de producto");
        ingresarMateriales = new TextField("Ingresar Materiales");
        botonAniadirMaterial = new Button();
        botonEliminarMaterial = new Button();
        botonAniadirMaterial.setIcon(new Icon("lumo", "plus"));
        botonEliminarMaterial.setIcon(new Icon("lumo", "minus"));
        botonesCRUD = new Button[]{

                new Button("Guardar"),
                new Button("Cancelar"),
                new Button("PDF"),

        };

        altaProductos = new Tab("Alta de productos");
        listaProductos = new Tab("Lista de procesos");

        tablaProductos = new Grid<>(Productos.class, false);
        tablaProductos.addColumn(Productos::getCodigoProducto).setHeader("Código Producto");
        tablaProductos.addColumn(Productos::getNombreProducto).setHeader("Nombre del Producto");
        tablaProductos.addColumn(Productos::getListaMateriales).setHeader("Materiales");
        botonAniadirMaterial.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {


                panelNuevosProcesos.add(new TextField());
                panelNuevosProcesos.getElement().executeJs("window.setTimeout(function() { $0.requestLayout(); }, 0);", panelProcesosYBotones.getElement());
            }
        });

        botonEliminarMaterial.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                panelNuevosProcesos.removeAll();
                panelNuevosProcesos.getElement().executeJs("window.setTimeout(function() { $0.requestLayout(); }, 0);", panelProcesosYBotones.getElement());

            }
        });
        menu.addSelectedChangeListener(event -> setContentOnTab(event.getSelectedTab()));
        botonEliminarMaterial.addThemeVariants(ButtonVariant.LUMO_SMALL);
        botonAniadirMaterial.addThemeVariants(ButtonVariant.LUMO_SMALL);
        panelNuevosProcesos.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        panelNuevosProcesos.setWidth("19%");
        panelProductos.add(seleccionProductos, codigoProducto);
        panelProcesosYBotones.add(ingresarMateriales, botonAniadirMaterial, botonEliminarMaterial);
        panelProcesosYBotones.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        panelProcesosYBotones.setWidth("60%");
        panelBoronesCRUD.add(botonesCRUD);
        menu.add(altaProductos, listaProductos);
        add(menu);
        add(panelContenidoAltaMateriales);
        add(panelContenidoTabla);



    }

    private void setContentOnTab(Tab tab) {

        panelContenidoAltaMateriales.removeAll();
        panelContenidoTabla.removeAll();


        if(tab.equals(altaProductos)){

            panelContenidoAltaMateriales.add(panelProductos, panelNuevosProcesos, panelProcesosYBotones, panelNuevosProcesos, panelBoronesCRUD);

        }else if(tab.equals(listaProductos)){

            panelContenidoTabla.add(tablaProductos);

        }

    }

}