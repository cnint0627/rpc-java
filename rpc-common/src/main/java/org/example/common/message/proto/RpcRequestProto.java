// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/RpcRequest.proto

package org.example.common.message.proto;

public final class RpcRequestProto {
  private RpcRequestProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RpcRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.example.common.message.proto.RpcRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string interface_name = 1;</code>
     * @return The interfaceName.
     */
    String getInterfaceName();
    /**
     * <code>string interface_name = 1;</code>
     * @return The bytes for interfaceName.
     */
    com.google.protobuf.ByteString
        getInterfaceNameBytes();

    /**
     * <code>string method_name = 2;</code>
     * @return The methodName.
     */
    String getMethodName();
    /**
     * <code>string method_name = 2;</code>
     * @return The bytes for methodName.
     */
    com.google.protobuf.ByteString
        getMethodNameBytes();

    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @return A list containing the params.
     */
    java.util.List<com.google.protobuf.ByteString> getParamsList();
    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @return The count of params.
     */
    int getParamsCount();
    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @param index The index of the element to return.
     * @return The params at the given index.
     */
    com.google.protobuf.ByteString getParams(int index);

    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @return A list containing the paramsType.
     */
    java.util.List<String>
        getParamsTypeList();
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @return The count of paramsType.
     */
    int getParamsTypeCount();
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @param index The index of the element to return.
     * @return The paramsType at the given index.
     */
    String getParamsType(int index);
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @param index The index of the value to return.
     * @return The bytes of the paramsType at the given index.
     */
    com.google.protobuf.ByteString
        getParamsTypeBytes(int index);
  }
  /**
   * Protobuf type {@code org.example.common.message.proto.RpcRequest}
   */
  public static final class RpcRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.example.common.message.proto.RpcRequest)
      RpcRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use RpcRequest.newBuilder() to construct.
    private RpcRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RpcRequest() {
      interfaceName_ = "";
      methodName_ = "";
      params_ = java.util.Collections.emptyList();
      paramsType_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new RpcRequest();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private RpcRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              String s = input.readStringRequireUtf8();

              interfaceName_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              methodName_ = s;
              break;
            }
            case 26: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                params_ = new java.util.ArrayList<com.google.protobuf.ByteString>();
                mutable_bitField0_ |= 0x00000001;
              }
              params_.add(input.readBytes());
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000002) != 0)) {
                paramsType_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000002;
              }
              paramsType_.add(s);
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          params_ = java.util.Collections.unmodifiableList(params_); // C
        }
        if (((mutable_bitField0_ & 0x00000002) != 0)) {
          paramsType_ = paramsType_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return RpcRequestProto.internal_static_org_example_common_message_proto_RpcRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RpcRequestProto.internal_static_org_example_common_message_proto_RpcRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RpcRequest.class, Builder.class);
    }

    public static final int INTERFACE_NAME_FIELD_NUMBER = 1;
    private volatile Object interfaceName_;
    /**
     * <code>string interface_name = 1;</code>
     * @return The interfaceName.
     */
    @Override
    public String getInterfaceName() {
      Object ref = interfaceName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        interfaceName_ = s;
        return s;
      }
    }
    /**
     * <code>string interface_name = 1;</code>
     * @return The bytes for interfaceName.
     */
    @Override
    public com.google.protobuf.ByteString
        getInterfaceNameBytes() {
      Object ref = interfaceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        interfaceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int METHOD_NAME_FIELD_NUMBER = 2;
    private volatile Object methodName_;
    /**
     * <code>string method_name = 2;</code>
     * @return The methodName.
     */
    @Override
    public String getMethodName() {
      Object ref = methodName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        methodName_ = s;
        return s;
      }
    }
    /**
     * <code>string method_name = 2;</code>
     * @return The bytes for methodName.
     */
    @Override
    public com.google.protobuf.ByteString
        getMethodNameBytes() {
      Object ref = methodName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        methodName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PARAMS_FIELD_NUMBER = 3;
    private java.util.List<com.google.protobuf.ByteString> params_;
    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @return A list containing the params.
     */
    @Override
    public java.util.List<com.google.protobuf.ByteString>
        getParamsList() {
      return params_;
    }
    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @return The count of params.
     */
    public int getParamsCount() {
      return params_.size();
    }
    /**
     * <pre>
     * 使用 bytes 存储参数，具体类型在反序列化时处理
     * </pre>
     *
     * <code>repeated bytes params = 3;</code>
     * @param index The index of the element to return.
     * @return The params at the given index.
     */
    public com.google.protobuf.ByteString getParams(int index) {
      return params_.get(index);
    }

    public static final int PARAMS_TYPE_FIELD_NUMBER = 4;
    private com.google.protobuf.LazyStringList paramsType_;
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @return A list containing the paramsType.
     */
    public com.google.protobuf.ProtocolStringList
        getParamsTypeList() {
      return paramsType_;
    }
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @return The count of paramsType.
     */
    public int getParamsTypeCount() {
      return paramsType_.size();
    }
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @param index The index of the element to return.
     * @return The paramsType at the given index.
     */
    public String getParamsType(int index) {
      return paramsType_.get(index);
    }
    /**
     * <pre>
     * 参数类型，存储为字符串
     * </pre>
     *
     * <code>repeated string params_type = 4;</code>
     * @param index The index of the value to return.
     * @return The bytes of the paramsType at the given index.
     */
    public com.google.protobuf.ByteString
        getParamsTypeBytes(int index) {
      return paramsType_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(interfaceName_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, interfaceName_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(methodName_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, methodName_);
      }
      for (int i = 0; i < params_.size(); i++) {
        output.writeBytes(3, params_.get(i));
      }
      for (int i = 0; i < paramsType_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, paramsType_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(interfaceName_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, interfaceName_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(methodName_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, methodName_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < params_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(params_.get(i));
        }
        size += dataSize;
        size += 1 * getParamsList().size();
      }
      {
        int dataSize = 0;
        for (int i = 0; i < paramsType_.size(); i++) {
          dataSize += computeStringSizeNoTag(paramsType_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getParamsTypeList().size();
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof RpcRequest)) {
        return super.equals(obj);
      }
      RpcRequest other = (RpcRequest) obj;

      if (!getInterfaceName()
          .equals(other.getInterfaceName())) return false;
      if (!getMethodName()
          .equals(other.getMethodName())) return false;
      if (!getParamsList()
          .equals(other.getParamsList())) return false;
      if (!getParamsTypeList()
          .equals(other.getParamsTypeList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + INTERFACE_NAME_FIELD_NUMBER;
      hash = (53 * hash) + getInterfaceName().hashCode();
      hash = (37 * hash) + METHOD_NAME_FIELD_NUMBER;
      hash = (53 * hash) + getMethodName().hashCode();
      if (getParamsCount() > 0) {
        hash = (37 * hash) + PARAMS_FIELD_NUMBER;
        hash = (53 * hash) + getParamsList().hashCode();
      }
      if (getParamsTypeCount() > 0) {
        hash = (37 * hash) + PARAMS_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getParamsTypeList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RpcRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RpcRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RpcRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static RpcRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RpcRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RpcRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(RpcRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.example.common.message.proto.RpcRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.example.common.message.proto.RpcRequest)
        RpcRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RpcRequestProto.internal_static_org_example_common_message_proto_RpcRequest_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RpcRequestProto.internal_static_org_example_common_message_proto_RpcRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RpcRequest.class, Builder.class);
      }

      // Construct using org.example.common.message.proto.RpcRequestOuterClass.RpcRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        interfaceName_ = "";

        methodName_ = "";

        params_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        paramsType_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RpcRequestProto.internal_static_org_example_common_message_proto_RpcRequest_descriptor;
      }

      @Override
      public RpcRequest getDefaultInstanceForType() {
        return RpcRequest.getDefaultInstance();
      }

      @Override
      public RpcRequest build() {
        RpcRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public RpcRequest buildPartial() {
        RpcRequest result = new RpcRequest(this);
        int from_bitField0_ = bitField0_;
        result.interfaceName_ = interfaceName_;
        result.methodName_ = methodName_;
        if (((bitField0_ & 0x00000001) != 0)) {
          params_ = java.util.Collections.unmodifiableList(params_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.params_ = params_;
        if (((bitField0_ & 0x00000002) != 0)) {
          paramsType_ = paramsType_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.paramsType_ = paramsType_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof RpcRequest) {
          return mergeFrom((RpcRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RpcRequest other) {
        if (other == RpcRequest.getDefaultInstance()) return this;
        if (!other.getInterfaceName().isEmpty()) {
          interfaceName_ = other.interfaceName_;
          onChanged();
        }
        if (!other.getMethodName().isEmpty()) {
          methodName_ = other.methodName_;
          onChanged();
        }
        if (!other.params_.isEmpty()) {
          if (params_.isEmpty()) {
            params_ = other.params_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureParamsIsMutable();
            params_.addAll(other.params_);
          }
          onChanged();
        }
        if (!other.paramsType_.isEmpty()) {
          if (paramsType_.isEmpty()) {
            paramsType_ = other.paramsType_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureParamsTypeIsMutable();
            paramsType_.addAll(other.paramsType_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        RpcRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RpcRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private Object interfaceName_ = "";
      /**
       * <code>string interface_name = 1;</code>
       * @return The interfaceName.
       */
      public String getInterfaceName() {
        Object ref = interfaceName_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          interfaceName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string interface_name = 1;</code>
       * @return The bytes for interfaceName.
       */
      public com.google.protobuf.ByteString
          getInterfaceNameBytes() {
        Object ref = interfaceName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          interfaceName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string interface_name = 1;</code>
       * @param value The interfaceName to set.
       * @return This builder for chaining.
       */
      public Builder setInterfaceName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        interfaceName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string interface_name = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearInterfaceName() {
        
        interfaceName_ = getDefaultInstance().getInterfaceName();
        onChanged();
        return this;
      }
      /**
       * <code>string interface_name = 1;</code>
       * @param value The bytes for interfaceName to set.
       * @return This builder for chaining.
       */
      public Builder setInterfaceNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        interfaceName_ = value;
        onChanged();
        return this;
      }

      private Object methodName_ = "";
      /**
       * <code>string method_name = 2;</code>
       * @return The methodName.
       */
      public String getMethodName() {
        Object ref = methodName_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          methodName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string method_name = 2;</code>
       * @return The bytes for methodName.
       */
      public com.google.protobuf.ByteString
          getMethodNameBytes() {
        Object ref = methodName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          methodName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string method_name = 2;</code>
       * @param value The methodName to set.
       * @return This builder for chaining.
       */
      public Builder setMethodName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        methodName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string method_name = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMethodName() {
        
        methodName_ = getDefaultInstance().getMethodName();
        onChanged();
        return this;
      }
      /**
       * <code>string method_name = 2;</code>
       * @param value The bytes for methodName to set.
       * @return This builder for chaining.
       */
      public Builder setMethodNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        methodName_ = value;
        onChanged();
        return this;
      }

      private java.util.List<com.google.protobuf.ByteString> params_ = java.util.Collections.emptyList();
      private void ensureParamsIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          params_ = new java.util.ArrayList<com.google.protobuf.ByteString>(params_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @return A list containing the params.
       */
      public java.util.List<com.google.protobuf.ByteString>
          getParamsList() {
        return ((bitField0_ & 0x00000001) != 0) ?
                 java.util.Collections.unmodifiableList(params_) : params_;
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @return The count of params.
       */
      public int getParamsCount() {
        return params_.size();
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @param index The index of the element to return.
       * @return The params at the given index.
       */
      public com.google.protobuf.ByteString getParams(int index) {
        return params_.get(index);
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @param index The index to set the value at.
       * @param value The params to set.
       * @return This builder for chaining.
       */
      public Builder setParams(
          int index, com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureParamsIsMutable();
        params_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @param value The params to add.
       * @return This builder for chaining.
       */
      public Builder addParams(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureParamsIsMutable();
        params_.add(value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @param values The params to add.
       * @return This builder for chaining.
       */
      public Builder addAllParams(
          Iterable<? extends com.google.protobuf.ByteString> values) {
        ensureParamsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, params_);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 使用 bytes 存储参数，具体类型在反序列化时处理
       * </pre>
       *
       * <code>repeated bytes params = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearParams() {
        params_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList paramsType_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureParamsTypeIsMutable() {
        if (!((bitField0_ & 0x00000002) != 0)) {
          paramsType_ = new com.google.protobuf.LazyStringArrayList(paramsType_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @return A list containing the paramsType.
       */
      public com.google.protobuf.ProtocolStringList
          getParamsTypeList() {
        return paramsType_.getUnmodifiableView();
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @return The count of paramsType.
       */
      public int getParamsTypeCount() {
        return paramsType_.size();
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param index The index of the element to return.
       * @return The paramsType at the given index.
       */
      public String getParamsType(int index) {
        return paramsType_.get(index);
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param index The index of the value to return.
       * @return The bytes of the paramsType at the given index.
       */
      public com.google.protobuf.ByteString
          getParamsTypeBytes(int index) {
        return paramsType_.getByteString(index);
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param index The index to set the value at.
       * @param value The paramsType to set.
       * @return This builder for chaining.
       */
      public Builder setParamsType(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureParamsTypeIsMutable();
        paramsType_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param value The paramsType to add.
       * @return This builder for chaining.
       */
      public Builder addParamsType(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureParamsTypeIsMutable();
        paramsType_.add(value);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param values The paramsType to add.
       * @return This builder for chaining.
       */
      public Builder addAllParamsType(
          Iterable<String> values) {
        ensureParamsTypeIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, paramsType_);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearParamsType() {
        paramsType_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 参数类型，存储为字符串
       * </pre>
       *
       * <code>repeated string params_type = 4;</code>
       * @param value The bytes of the paramsType to add.
       * @return This builder for chaining.
       */
      public Builder addParamsTypeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureParamsTypeIsMutable();
        paramsType_.add(value);
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:org.example.common.message.proto.RpcRequest)
    }

    // @@protoc_insertion_point(class_scope:org.example.common.message.proto.RpcRequest)
    private static final RpcRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RpcRequest();
    }

    public static RpcRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RpcRequest>
        PARSER = new com.google.protobuf.AbstractParser<RpcRequest>() {
      @Override
      public RpcRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RpcRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RpcRequest> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<RpcRequest> getParserForType() {
      return PARSER;
    }

    @Override
    public RpcRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_example_common_message_proto_RpcRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_example_common_message_proto_RpcRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026proto/RpcRequest.proto\022 org.example.co" +
      "mmon.message.proto\"^\n\nRpcRequest\022\026\n\016inte" +
      "rface_name\030\001 \001(\t\022\023\n\013method_name\030\002 \001(\t\022\016\n" +
      "\006params\030\003 \003(\014\022\023\n\013params_type\030\004 \003(\tb\006prot" +
      "o3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_org_example_common_message_proto_RpcRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_example_common_message_proto_RpcRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_example_common_message_proto_RpcRequest_descriptor,
        new String[] { "InterfaceName", "MethodName", "Params", "ParamsType", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
