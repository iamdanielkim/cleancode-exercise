package ex4.logmethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class AssetReport {

    private Map<String, BigDecimal> positions;
    private Assessor assessor;
    private Map<String, BigDecimal> groupTotal;
    private Map m_hmRiskTable;

    public AssetReport(Map<String, BigDecimal> positions, Assessor assessor, Map<String, BigDecimal> groupTotal) {
        this.positions = positions;
        this.assessor = assessor;
        this.groupTotal = groupTotal;
    }

    public void execute(RecordSet records, PrintWriter output) throws IOException {
        BigDecimal allPositions = new BigDecimal("0.00");
        Map<String, String> assetToGroup = new HashMap<String, String>();
        for (int row = 0; row < records.getRowCount(); row++) {
            BigDecimal pos;
            BigDecimal r = new BigDecimal("0.00");
            String issue = "";
            issue = records.getItem(row, "ISSUE_NAME");
            if (records.getItem(row, "ISSUE_FAMILY").toUpperCase().startsWith("FUND")) {
                BigDecimal perItem = records.getDecimal(row, "MARKET_PRICE").subtract(records.getDecimal(row, "TERM_ONE"));
                pos = perItem.multiply(records.getDecimal(row, "QUANTITY")).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal riskCoefficient = assessor.getRiskCoefficient(records.getItem(row, "ISSUE_FAMILY"), records
                        .getDecimal(row, "TERM_TWO"));
                BigDecimal product = riskCoefficient.multiply(pos);
                r = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
                positions.put(issue, pos);
            } else {
                pos = records.getDecimal(row, "QUANTITY").multiply(records.getDecimal(row, "MARKET_PRICE"));
                pos = pos.subtract(records.getDecimal(row, "TERM_ONE")).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal product = records.getDecimal(row, "TERM_TWO").multiply(pos);
                r = product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
                positions.put(issue, pos);
            }
            allPositions = allPositions.add(positions.get(issue));
            String group = records.getItem(row, "ISSUE_GROUP");
            String name = records.getItem(row, "ISSUE_NAME");
            assetToGroup.put(name, group);
            BigDecimal value = new BigDecimal("0");
            if (groupTotal.containsKey(group))
                value = value.add(groupTotal.get(group)).setScale(2);
            value = value.add(positions.get(issue));
            groupTotal.put(group, value.setScale(2));
            m_hmRiskTable.put(issue, r);
        }
        output.write("<groups>\n");
        Iterator<String> g = groupTotal.keySet().iterator();
        while (g.hasNext()) {
            String grp = g.next();
            BigDecimal position = groupTotal.get(grp);
            BigDecimal product = position.multiply(new BigDecimal(100));
            BigDecimal weight = product.divide(allPositions, 2, BigDecimal.ROUND_HALF_UP);
            output.write("\t<group position='" + position.toPlainString());
            output.write("' weight='" + weight);
            output.write("'>\n");
            output.write("\t\t" + grp + "\n");
            Iterator<String> i = positions.keySet().iterator();
            boolean notFirstOne = false;
            while (i.hasNext()) {
                String asset = i.next();
                if (assetToGroup.get(asset).equalsIgnoreCase(grp)) {
                    if (notFirstOne)
                        output.write("\n");
                    output.write("\t\t<asset position='" + positions.get(asset).toPlainString() + "' ");
                    BigDecimal p = positions.get(asset);
                    BigDecimal weight1 = p.multiply(new BigDecimal("100.00")).divide(position, 2, BigDecimal.ROUND_HALF_UP)
                            .setScale(2);
                    output.write("weight='" + weight1 + "' risk='" + m_hmRiskTable.get(asset) + "'>\n");
                    output.write("\t\t\t" + asset + "\n");
                    output.write("\t\t</asset>");
                    notFirstOne = true;
                }
            }
            output.write("\n\t</group>\n");
        }
        output.write("</groups>\n");
    }

}

interface Assessor {
    BigDecimal getRiskCoefficient(String issue_family, BigDecimal term_two);
}

interface RecordSet {

    public String getItem(int row, String issue_name);

    public BigDecimal getDecimal(int row, String market_price);

    public int getRowCount();
}


