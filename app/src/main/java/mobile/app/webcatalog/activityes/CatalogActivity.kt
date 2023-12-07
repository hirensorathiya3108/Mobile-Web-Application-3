package mobile.app.webcatalog.activityes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobile.app.webcatalog.Catalog
import mobile.app.webcatalog.R
import mobile.app.webcatalog.databinding.ActivityCatalogBinding
import mobile.app.webcatalog.databinding.ItemListBinding

class CatalogActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCatalogBinding
    private lateinit var activity: Activity
    private lateinit var catlogList: ArrayList<Catalog>
    private lateinit var catalogAdapter: CatalogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catalog)
        activity = this@CatalogActivity
        initViews()
    }

    private fun initViews() {
        catlogList = ArrayList()
        catlogList.add(Catalog(name = activity.getString(R.string.title1), image = R.drawable.ic_bald_eagle, description = activity.getString(R.string.desc1)))
        catlogList.add(Catalog(name = activity.getString(R.string.title2), image = R.drawable.ic_american_alligator, description = activity.getString(R.string.desc2)))
        catlogList.add(Catalog(name = activity.getString(R.string.title3), image = R.drawable.ic_coyote, description = activity.getString(R.string.desc3)))
        catlogList.add(Catalog(name = activity.getString(R.string.title4), image = R.drawable.ic_seal, description = activity.getString(R.string.desc4)))
        catlogList.add(Catalog(name = activity.getString(R.string.title5), image = R.drawable.ic_bear, description = activity.getString(R.string.desc5)))
        catalogAdapter = CatalogAdapter(list = catlogList)
        binding.catLogRecyclerView.adapter = catalogAdapter
        binding.catLogRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding.catLogRecyclerView.setHasFixedSize(true)

        binding.btnGoBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGoBack -> {
                super.onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    inner class CatalogAdapter(var list: ArrayList<Catalog>) :
        RecyclerView.Adapter<CatalogAdapter.ListItemHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
            return ListItemHolder(ItemListBinding.inflate(LayoutInflater.from(activity),parent,false))
        }

        override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
            val item = list[position]
            holder.binding.tvCatTitle.text = item.name
            holder.binding.ivAnimal.setImageDrawable(ContextCompat.getDrawable(activity,item.image))
            holder.binding.tvCatalogDesc.text = item.description
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class ListItemHolder(binding: ItemListBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val binding = binding
        }
    }
}