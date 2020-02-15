package com.jetpack.first.ui.fragment.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkInfo
import com.jetpack.first.R
import com.jetpack.first.common.BaseConstant.KEY_IMAGE_URI
import com.jetpack.first.viewmodel.MeModel
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.jetbrains.anko.support.v4.toast
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.hasPermissions
import timber.log.Timber

class MeFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private var RC_CAMERA_PERM: Int = 122
    private lateinit var meViewModel: MeModel

    private val REQUEST_CODE_IMAGE = 100
    private val REQUEST_CODE_PERMISSIONS = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel =
            ViewModelProviders.of(this).get(MeModel::class.java)

        meViewModel.outputWorkInfoItems.observe(this, workInfosObserver())
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectImage.setOnClickListener {
            requirePermission()
        }
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                // 更新头像
                if (!outputImageUri.isNullOrEmpty()) {
                    meViewModel.setOutputUri(outputImageUri)
                    // TODO
                    blurImageView.setImageURI(Uri.parse(outputImageUri))
                }
            }
        }
    }

    /**
     * 选择图片
     */
    private fun selectImage() {
        val chooseIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(chooseIntent, REQUEST_CODE_IMAGE)
    }

    /** Image Selection  */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> data?.let { handleImageRequestResult(data) }
                else -> Timber.d("Unknown request code.")
            }
        } else {
            Timber.e("Unexpected Result code $resultCode")
        }
    }

    private fun handleImageRequestResult(intent: Intent) {
        // If clipdata is available, we use it, otherwise we use data
        val imageUri: Uri? = intent.clipData?.let {
            it.getItemAt(0).uri
        } ?: intent.data

        if (imageUri == null) {
            Timber.e("Invalid input image Uri.")
            return
        }

        imageView.setImageURI(imageUri)
        // 图片模糊处理
        meViewModel.setImageUri(imageUri.toString())
        meViewModel.applyBlur(3)
    }

    @AfterPermissionGranted(122)
    private fun requirePermission() {
        if (hasPermissions(requireContext(), Manifest.permission.CAMERA)) {
            selectImage()
        } else {
            EasyPermissions.requestPermissions(
                this, "App需要你的头像",
                RC_CAMERA_PERM, Manifest.permission.CAMERA
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        toast("onPermissionsDenied")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        selectImage()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}