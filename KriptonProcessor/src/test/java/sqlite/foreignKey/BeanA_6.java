package sqlite.foreignKey;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class BeanA_6 {

	@BindColumn(value="pk", columnType=ColumnType.PRIMARY_KEY)
	public long id;
	
	@BindColumn(foreignKey=BeanA_5.class, nullable=false)
	public Long beanA2Id;
	
	public String valueString2;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((valueString2 == null) ? 0 : valueString2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanA_6 other = (BeanA_6) obj;
		if (id != other.id)
			return false;
		if (valueString2 == null) {
			if (other.valueString2 != null)
				return false;
		} else if (!valueString2.equals(other.valueString2))
			return false;
		return true;
	}
}

