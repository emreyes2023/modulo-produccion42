package com.springApplication.moduloProduccion.views;

import com.springApplication.moduloProduccion.models.EstadosProduccion;
import com.springApplication.moduloProduccion.models.Orden;
import com.springApplication.moduloProduccion.models.Productos;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.checkbox.CheckboxGroup;


@PageTitle("Órdenes de Producción")
@Route(value = "ordenesProduccion", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class OrdenesProduccionView extends VerticalLayout {

    private TextField numeroProduccion;
    private ComboBox<EstadosProduccion> estados;
    private TextField cliente;
    private TextField usuario;
    private DatePicker calendarioInicio;
    private DatePicker calendarioFinal;
    private ComboBox<Productos> seleccionProductos;
    private IntegerField cantidad;
    private TextArea especificaciones;
    private CheckboxGroup<String> procesosProduccion;
    private Button[] botonesCRUD;
    private Button[] botonesArchivos;
    private Tab crudTab;
    private Tab consultarOrdenesTab;
    private Tabs menu;
    private Grid<Orden> tablaOrdenes;
    private VerticalLayout panel = new VerticalLayout();
    private FormLayout layoutOrden= new FormLayout();
    private FormLayout layoutFechas = new FormLayout();
    private FormLayout layoutProductos = new FormLayout();
    private HorizontalLayout layoutBotones = new HorizontalLayout();
    private HorizontalLayout panelProduccion = new HorizontalLayout();
    private VerticalLayout layoutBotonesArchivos = new VerticalLayout();
    private VerticalLayout panelTablaConsulta = new VerticalLayout();

    public OrdenesProduccionView() {

        initComponents();

    }

    private void initComponents(){


        numeroProduccion = new TextField("N.P");
        estados = new ComboBox<>("Estado:");
        cliente = new TextField("Cliente");
        usuario = new TextField("Usuario");
        calendarioInicio = new DatePicker("Fecha Estimada de Inicio");
        calendarioFinal = new DatePicker("Fecha Estimada de Finalización");
        seleccionProductos = new ComboBox<>("Seleccionar productos");
        cantidad = new IntegerField("Cantidad");
        cantidad.setMin(1);
        cantidad.setMax(1000);
        cantidad.setStepButtonsVisible(true);
        especificaciones = new TextArea("Especificaciones");
        botonesCRUD = new Button[]{

                new Button("Agregar Orden"),
                new Button("Añadir Producto"),
                new Button("Eliminar Orden"),
                new Button("Modificar Orden"),
                new Button("Consultar Ordenes")

        };

        botonesCRUD[0].setIcon(new Icon("lumo", "plus"));
        botonesCRUD[1].setIcon(new Icon("lumo", "minus"));
        botonesCRUD[2].setIcon(new Icon("vaadin", "trash"));
        botonesCRUD[3].setIcon(new Icon("vaadin", "pencil"));
        botonesCRUD[4].setIcon(new Icon("vaadin", "search"));

        botonesArchivos = new Button[] {

                new Button("Guardar"),
                new Button("Cancelar"),
                new Button("PDF")

        };
        crudTab = new Tab("CRUD Ordenes de producción");
        consultarOrdenesTab = new Tab("Consultar Órdenes");
        tablaOrdenes = new Grid<>();
        tablaOrdenes.addColumn(Orden::getNumeroProduccion).setHeader("NP");
        tablaOrdenes.addColumn(Orden::getEstado).setHeader("Estado");
        tablaOrdenes.addColumn(Orden::getCliente).setHeader("Cliente");
        tablaOrdenes.addColumn(Orden::getUsuario).setHeader("Usuario");
        tablaOrdenes.addColumn(Orden::getFechaInicio).setHeader("Fecha Inicio");
        tablaOrdenes.addColumn(Orden::getFechaFinal).setHeader("Fecha Final");
        tablaOrdenes.addColumn(Orden::getDetalle).setHeader("Detalle");
        menu = new Tabs();
        menu.add(crudTab, consultarOrdenesTab);
        menu.addSelectedChangeListener(event -> setContentOnTab(event.getSelectedTab()));

        estados.setItems(EstadosProduccion.values());
        layoutOrden.add(numeroProduccion, estados, cliente, usuario);
        layoutOrden.setWidth("70%");
        layoutFechas.add(calendarioInicio, calendarioFinal);
        layoutFechas.setWidth("70%");
        layoutProductos.add(seleccionProductos, cantidad, especificaciones);
        layoutProductos.setWidth("70%");
        layoutBotones.add(botonesCRUD);
        layoutBotones.setHeight("20%");


        layoutOrden.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4));
        layoutFechas.setResponsiveSteps(new FormLayout.ResponsiveStep("50px", 2));
        layoutProductos.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));

        procesosProduccion = new CheckboxGroup<>("Seleccionar procesos");
        procesosProduccion.setItems("Corte", "Construccion", "Barnizado", "Empaquetado");
        procesosProduccion.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        panelProduccion.add(panel, layoutBotonesArchivos);
        panel.add(layoutOrden, layoutFechas, layoutProductos, procesosProduccion, layoutBotones);
        panel.setWidth("70%");
        layoutBotonesArchivos.add(botonesArchivos);
        layoutBotonesArchivos.setAlignItems(Alignment.START);
        layoutBotonesArchivos.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
        layoutBotonesArchivos.setWidth("80%");
        layoutBotonesArchivos.setHeight("30%");

        add(menu);
        add(panelProduccion);
        add(panelTablaConsulta);



    }

    private void setContentOnTab(Tab tab) {


        panelProduccion.removeAll();

        panelTablaConsulta.removeAll();


        if(tab.equals(crudTab)){

            panelProduccion.add(panel, layoutBotonesArchivos);



        }else if(tab.equals(consultarOrdenesTab)){

            panelTablaConsulta.add(tablaOrdenes);


        }

    }

}
