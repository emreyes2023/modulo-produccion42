package com.springApplication.moduloProduccion.views;


import com.springApplication.moduloProduccion.models.Procesos;
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


@PageTitle("Alta de procesos")
@Route(value = "altaprocesos", layout = MainLayout.class)
public class AltaProcesosView extends VerticalLayout {

    private ComboBox<Productos> seleccionProductos;
    private TextField codigoProducto;
    private TextField ingresarProcesos;
    private Button botonEliminarProceso;
    private Button botonAniadirProceso;
    private Button[] botonesCRUD;
    private Tab altaProcesos;
    private Tab listaProcesos;
    private Grid<Procesos> tablaProcesos;
    private HorizontalLayout panelProductos = new HorizontalLayout();
    private FormLayout panelProcesosYBotones = new FormLayout();
    private FormLayout panelNuevosProcesos = new FormLayout();
    private HorizontalLayout panelBoronesCRUD = new HorizontalLayout();
    private VerticalLayout panelContenidoAltaProcesos = new VerticalLayout();
    private VerticalLayout panelContenidoTablaProcesos = new VerticalLayout();
    private Tabs menu = new Tabs();


    public AltaProcesosView() {

        initComponents();

    }

    private void initComponents(){

        seleccionProductos = new ComboBox<>("Selección de productos");
        codigoProducto = new TextField("Código de producto");
        ingresarProcesos = new TextField("Ingresar Procesos");
        botonAniadirProceso = new Button();
        botonEliminarProceso = new Button();
        botonAniadirProceso.setIcon(new Icon("lumo", "plus"));
        botonEliminarProceso.setIcon(new Icon("lumo", "minus"));
        botonesCRUD = new Button[]{

                new Button("Guardar"),
                new Button("Cancelar"),
                new Button("PDF"),

        };

        altaProcesos = new Tab("Alta de procesos");
        listaProcesos = new Tab("Consulta de procesos");

        tablaProcesos = new Grid<>(Procesos.class, false);
        tablaProcesos.addColumn(Procesos::getProcesos).setHeader("Código Producto");
        tablaProcesos.addColumn(Procesos::getNombreProducto).setHeader("Nombre del Producto");
        tablaProcesos.addColumn(Procesos::getProcesos).setHeader("Procesos");
        botonAniadirProceso.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {


                panelNuevosProcesos.add(new TextField());
                panelNuevosProcesos.getElement().executeJs("window.setTimeout(function() { $0.requestLayout(); }, 0);", panelProcesosYBotones.getElement());
            }
        });

        botonEliminarProceso.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                panelNuevosProcesos.removeAll();
                panelNuevosProcesos.getElement().executeJs("window.setTimeout(function() { $0.requestLayout(); }, 0);", panelProcesosYBotones.getElement());

            }
        });
        menu.addSelectedChangeListener(event -> setContentOnTab(event.getSelectedTab()));
        botonEliminarProceso.addThemeVariants(ButtonVariant.LUMO_SMALL);
        botonAniadirProceso.addThemeVariants(ButtonVariant.LUMO_SMALL);
        panelNuevosProcesos.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        panelNuevosProcesos.setWidth("19%");
        panelProductos.add(seleccionProductos, codigoProducto);
        panelProcesosYBotones.add(ingresarProcesos, botonAniadirProceso, botonEliminarProceso);
        panelProcesosYBotones.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        panelProcesosYBotones.setWidth("60%");
        panelBoronesCRUD.add(botonesCRUD);
        menu.add(altaProcesos, listaProcesos);
        add(menu);
        add(panelContenidoAltaProcesos);
        add(panelContenidoTablaProcesos);



    }

    private void setContentOnTab(Tab tab) {

        panelContenidoAltaProcesos.removeAll();
        panelContenidoTablaProcesos.removeAll();


        if(tab.equals(altaProcesos)){

            panelContenidoAltaProcesos.add(panelProductos, panelProcesosYBotones, panelNuevosProcesos, panelBoronesCRUD);

        }else if(tab.equals(listaProcesos)){

            panelContenidoTablaProcesos.add(tablaProcesos);

        }

    }
}
