package co.edu.uniquindio.subastaQuindio.persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FechaUtil {
	public static final long MILISEGUNDOS_POR_DIA = 86400000;
    public static final long MILISEGUNDOS_POR_HORA = 3600000;
    public static final long MILISEGUNDOS_POR_MINUTO = 60000;
    public static final long MILISEGUNDOS_POR_SEGUNDO = 1000;
    public static final String HORA_MINUTO_SEGUNDO = "HH:mm:ss";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DD_MM_YYYY_GUION = "dd-MM-yyyy";
    public static final String YYYY_MM_DD_GUION = "yyyy-MM-dd";

    public static String fechaUtilPersisteciaBackup() {
    	Date FechaUtil = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("_ddMMyyyy_HH_mm_ss");

        return formato.format(FechaUtil);
    }
    
    public static String FechaUtilActual() {
        Date FechaUtil = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(FechaUtil);
    }

    public static String FechaUtilActual(String style) {
        Date FechaUtil = new Date();
        SimpleDateFormat formato = new SimpleDateFormat(style);
        return formato.format(FechaUtil);
    }

    public static Calendar FechaUtilActualCalendar() {
        return Calendar.getInstance();
    }

    public static java.util.Date FechaUtilActualDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println(calendar.getTimeZone());
        return calendar.getTime();
    }

    public static String FechaUtilHoraMinutoSegundoActual() {
        Date FechaUtil = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return formato.format(FechaUtil);
    }

    public static String FechaUtilHomaMinutoSegundoActualParaNovedades() {
        Date FechaUtil = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");

        return formato.format(FechaUtil);
    }

    public static String parseToString(Date FechaUtil) {
        if (FechaUtil == null) {
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(FechaUtil);
    }

    public static String parseToStringFechaUtilHora(Date FechaUtil) {
        if (FechaUtil == null) {
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.format(FechaUtil);
    }

    public static Calendar parseToCalendar(Date FechaUtil) {
        Calendar c = new GregorianCalendar();
        c.setTime(FechaUtil);
        return c;
    }

    public static Calendar parseToCalendar(String FechaUtil) {
        Date d = parseToDate(FechaUtil);
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        return c;
    }

    /*
     * @param -FechaUtil: la FechaUtil a convertir a String
     * @param -formatoFechaUtil: el formato en String a utilizar
     * para convertir la FechaUtil, por ejemplo: "dd/MM/yyyy", para usar sólo la FechaUtil.
     * "hh:mm:ss", para obtener sólo la hora minuto y segundo. También se puede hacer cualquier
     * convinación de estos, por ejemplo: "'La FechaUtil es 'dd/MM/yyyy' y la hora es 'hh:mm:ss".
     * @return la FechaUtil convertida a String segun el formato indicado
     */
    public static String parseToString(Date FechaUtil, String formatoFechaUtil) {
        SimpleDateFormat formato = new SimpleDateFormat(formatoFechaUtil);
        return formato.format(FechaUtil);
    }

    public static String parseToString(Date FechaUtil, SimpleDateFormat formato) {
        return formato.format(FechaUtil);
    }

    public static Date parseToDate(String FechaUtil) {
        Date f = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            f = formato.parse(FechaUtil);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDateConHoraMinuto(String FechaUtil) {
        Date f = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            f = formato.parse(FechaUtil);
        } catch (ParseException ex) {
        }
        return f;
    }

    public static String parseToString(long FechaUtil) {
        String s = "";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            s = formato.format(new Date(FechaUtil));
        } catch (Exception ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static Date parseToDate(long FechaUtil) {
        Date f = new Date(FechaUtil);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String s = formato.format(f);
            f = formato.parse(s);
        } catch (Exception ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDate(long FechaUtil, String format) {
        Date f = new Date(FechaUtil);
        SimpleDateFormat formato = new SimpleDateFormat(format);
        try {
            String s = formato.format(f);
            f = formato.parse(s);
        } catch (Exception ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDate(String FechaUtil, String formatoFechaUtil) {
        Date f = null;
        SimpleDateFormat formato = new SimpleDateFormat(formatoFechaUtil);
        try {
            f = formato.parse(FechaUtil);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDate(String FechaUtil, SimpleDateFormat formato) {
        Date f = null;

        try {
            f = formato.parse(FechaUtil);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }


        return f;
    }

    public static java.sql.Date parseToDateSQL(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Time parseToTimeSQL(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Time(date.getTime());
    }

    public static Date parseToHourMinuteSecond(String FechaUtil) {
        Date f = null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        try {
            f = formato.parse(FechaUtil);
        } catch (ParseException ex) {
            Logger.getLogger(FechaUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static String parseToHourMinuteSecond(Date FechaUtil) {
        //SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        DateFormat formato = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        return formato.format(FechaUtil);
    }

    public static int diferenciaEnDias(Date FechaUtilInicial, Date FechaUtilFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String FechaUtilInicioString = df.format(FechaUtilInicial);
        String FechaUtilFinalString = df.format(FechaUtilFinal);
        try {
            FechaUtilInicial = df.parse(FechaUtilInicioString);
            FechaUtilFinal = df.parse(FechaUtilFinalString);
        } catch (ParseException ex) {
        }

        long FechaUtilInicialMs = FechaUtilInicial.getTime();
        long FechaUtilFinalMs = FechaUtilFinal.getTime();
        long diferencia = FechaUtilFinalMs - FechaUtilInicialMs;
        double dias = Math.floor(diferencia / MILISEGUNDOS_POR_DIA);
        return ((int) dias);
    }

    public static Date diferenciaEnMinutos(Date FechaUtilInicio, Date FechaUtilFin) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(FechaUtilFin);
        calendar.add(Calendar.MINUTE, -FechaUtilInicio.getMinutes());

        return calendar.getTime();
    }

    public static Date diferenciaEnHoras(Date FechaUtilInicio, Date FechaUtilFin) {
        System.out.println("Inicio: " + FechaUtilInicio);
        System.out.println("Fin: " + FechaUtilFin);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(FechaUtilFin);
        calendar.add(Calendar.HOUR_OF_DAY, -FechaUtilInicio.getHours());

        return calendar.getTime();
    }

    public static Calendar addDias(Calendar FechaUtil, int dias) {
        FechaUtil.add(Calendar.DAY_OF_YEAR, dias);
        return FechaUtil;
    }

    public static Date diferenciaEnSegundos(Date FechaUtilInicio, Date FechaUtilFin) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(FechaUtilFin);
        calendar.add(Calendar.SECOND, -FechaUtilInicio.getSeconds());

        return calendar.getTime();
    }

    public static Date diferenciaEnSegundosMinutosHoras(Date FechaUtilInicio, Date FechaUtilFin) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(FechaUtilFin);
        calendar.add(Calendar.SECOND, -FechaUtilInicio.getSeconds());
        calendar.add(Calendar.MINUTE, -FechaUtilInicio.getMinutes());
        calendar.add(Calendar.HOUR_OF_DAY, -FechaUtilInicio.getHours());
        return calendar.getTime();
    }

    public static Date diferenciaEnMinutosHoras(Date FechaUtilInicio, Date FechaUtilFin) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(FechaUtilFin);
        calendar.add(Calendar.MINUTE, -FechaUtilInicio.getMinutes());
        calendar.add(Calendar.HOUR_OF_DAY, -FechaUtilInicio.getHours());
        return calendar.getTime();
    }

    

//    

    public static boolean esMismaFechaUtil(Date a, Date b) {
        Calendar calA = new GregorianCalendar();
        Calendar calB = new GregorianCalendar();

        calA.setTime(a);
        calB.setTime(b);

        if (calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR)
                && calA.get(Calendar.MONTH) == calB.get(Calendar.MONTH)
                && calA.get(Calendar.DAY_OF_MONTH) == calB.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    public static Date setHoraMinutoSegundo(Date FechaUtil, Date hora) {
        GregorianCalendar gcFechaUtilInicio = new GregorianCalendar();

        gcFechaUtilInicio.setTime(FechaUtil);

        gcFechaUtilInicio.set(Calendar.HOUR_OF_DAY, hora.getHours());
        gcFechaUtilInicio.set(Calendar.MINUTE, hora.getMinutes());
        gcFechaUtilInicio.set(Calendar.SECOND, hora.getSeconds());

        return gcFechaUtilInicio.getTime();
    }

    public static Date calcularFechaUtilFin(Date FechaUtilActual, Date FechaUtilInicioPrevista, Date FechaUtilfinprevista) {
        GregorianCalendar inicio = (GregorianCalendar) FechaUtil.parseToCalendar(FechaUtilInicioPrevista);
//        System.out.println(FechaUtil.parseToString(inicio.getTime()));
        GregorianCalendar fin = (GregorianCalendar) FechaUtil.parseToCalendar(FechaUtilfinprevista);
//        System.out.println(FechaUtil.parseToString(fin.getTime()));
        int year = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int month = fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        int day = fin.get(Calendar.DATE) - inicio.get(Calendar.DATE);
        inicio.add(Calendar.YEAR, year);
        GregorianCalendar actual = (GregorianCalendar) FechaUtil.parseToCalendar(FechaUtilActual);
//        System.out.println(FechaUtil.parseToString(actual.getTime()));
        actual.add(Calendar.YEAR, year);
        actual.add(Calendar.MONTH, month);
        actual.add(Calendar.DATE, day);
//        System.out.println(FechaUtil.parseToString(actual.getTime()));
        return actual.getTime();
    }
   
}
