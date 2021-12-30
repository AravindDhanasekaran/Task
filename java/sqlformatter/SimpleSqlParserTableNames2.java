package sqlformatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.util.TablesNamesFinder;

public class SimpleSqlParserTableNames2 {
	
    public JSONObject check(String query) {
 	   Statement statement;
 	   List<String> tablelist = Arrays.asList("Customers","Suppliers","ProductTable");
 	   List<String> columnlist = Arrays.asList("SupplierName", "Country","ProductCode","ProductID");
 	   JSONObject obj = new JSONObject();
 	   
	try {
		statement = CCJSqlParserUtil.parse(query);
		TablesNamesFinderExt finder = new TablesNamesFinderExt();
 
	        
	       obj.put("isTablePresent", tablelist.containsAll(finder.getTableList(statement)));
	       obj.put("isColumnPresent", columnlist.containsAll(finder.getColumList()));
	    //  System.out.println(tablelist.containsAll(finder.getTableList(statement)));
	        
	        
	        
	} catch (JSQLParserException e) {
		// TODO Auto-generated catch block

	}
	return obj;
       
        
    }

    static class TablesNamesFinderExt extends TablesNamesFinder {
        List<String> mySelectTableList = new ArrayList<String>();
        List<String> mycolumList = new ArrayList<String>();
        boolean inSelect = true;

        public List<String> getTableList(Insert insert) {
            List<String> list = super.getTableList(insert);
            if (insert.getSelect() != null) {
                insert.getSelect().getSelectBody().accept(this);
            }
            return list;
        }
        
        
        
        

        @Override
        public void visit(SubSelect subSelect) {
            inSelect = true;
            super.visit(subSelect);
        }

        @Override
        public void visit(Table tableName) {
            super.visit(tableName); 
            if (inSelect && !mySelectTableList.contains(tableName.getFullyQualifiedName()))
                mySelectTableList.add(tableName.getFullyQualifiedName());
        }
        @Override
        public void visit(Column tableColumn) {
        	 if (inSelect && !mycolumList.contains(tableColumn.getFullyQualifiedName()))
        		 mycolumList.add(tableColumn.getFullyQualifiedName());
            //System.out.println(tableColumn);
        }

        public List<String> getSelectTableList() {
            return mySelectTableList;
        }
        public List<String> getColumList() {
            return mycolumList;
        }

    }
}