package adapter;



import com.bizinfocus.happycityapp.Menu1Fragment;
import com.bizinfocus.happycityapp.Menu2Fragment;
import com.bizinfocus.happycityapp.Menu3Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MenuFragmentAdapter extends FragmentPagerAdapter {

	public MenuFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			return new Menu1Fragment();
		case 1:
			return new Menu2Fragment();
		case 2:
			return new Menu3Fragment();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
